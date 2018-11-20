/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Monica y Paula
 */
public class Cliente {
    //Codigos:
    private static String IDE = "025"; //Codigo de identificacion
    private static String OK = "417";
    private static String ERROR = "427";

    private static String MenuInicial(){
        System.out.println("Escoga una opcion");
        System.out.println("1 - Registrarse\n2 - Identificarse");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();

        System.out.println("Inserte nombre usuario: ");
        String nombre = sc.nextLine();

        System.out.println("Inserte nombre password: ");
        String pass = sc.nextLine();

        String mensaje = "";
        switch (opcion){
            case "1":
                mensaje="133"+"|"+nombre+"|"+pass;
                break;
            case "2":
                mensaje="025"+"|"+nombre+"|"+pass;
                break;
            default:
                System.out.println("Error. Eso no es una opcion.");
                break;
        }
        return mensaje;
    }

    private static String MenuSalas(){
        System.out.println("A que sala quieres conectarte:\n 1 - Sala Celeste\n 2 - Sala Azafran\n 3 - Sala Lavacalda");
        Scanner sc = new Scanner(System.in);
        String opcion = sc.nextLine();
        String mensaje = "";
        switch (opcion){
            case "1":
                mensaje="037";
                break;
            case "2":
                mensaje="039";
                break;
            case "3":
                mensaje="216";
                break;
            default:
                System.out.println("Error. Eso no es una opcion.");
                break;
        }
        return mensaje;
    }

    public static void main(String[] args) {
        //Host donde se ejecuta
        String host="localhost";
        //Puerto en el que se espera al servidor
        int port = 8989;

        //Socket para la conexion TCP
        Socket socketServicio=null;

        //Para leer de teclado
        Scanner sc = new Scanner(System.in);

        //Autentificacion correcta
        boolean puede_seguir = false;
        //Quiere enviar mensajes
        boolean conectado = false;

        try{
            //Creamos un socket que se conecte con host y port
            socketServicio = new Socket(host,port);

            InputStream inputStream = socketServicio.getInputStream();
            OutputStream outputStream = socketServicio.getOutputStream();

            //Identificarse:
            String identificarse = MenuInicial();

            //Enviamos el array de identificacion
            System.out.println("Array a enviar: " + identificarse);
            PrintWriter outPrinter = new PrintWriter(outputStream, true);
            outPrinter.println(identificarse);
            System.out.println("Array de identificacion enviado al servidor.");
            //Para obligar al tcp a que haga el envio usamos .flush();
            outputStream.flush();

            //Recibimos el mensaje
            BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
            String todoCorrecto = inReader.readLine();
            System.out.println("Array recibido: " + todoCorrecto);

            //Comprobamos si es correcto o no
            switch (todoCorrecto){
                //Codigo de que la identificacion esta bien
                case "417": //parchirisu
                    System.out.println("Usuario identificado.");
                    puede_seguir = true;
                    break;
                //Codigo de Error en la autentificacion.
                case "427": //buneary
                    System.out.println("Este usuario no es correcto. Intentelo de nuevo.");
                    break;
                //Codigo de que la creacion esta bien
                case "155": //cyndaquil
                    System.out.println("Usuario ya cogido, porfavor intentelo de nuevo.");
                    break;
                //Codigo de error en la creacion.
                case "152": //chikorita
                    System.out.println("Usuario creado con exito y conectado.");
                    puede_seguir = true;
                    break;
                default:
                    System.out.println("ERROR");
                    break;
            }

            if(puede_seguir){
                String sala = MenuSalas();
                outPrinter.println(sala);
                //Aqui va un mensaje de que se ha conectado debidamente a la sala.
                conectado = true;
                
                ThreadRecibirChat hiloChat = new ThreadRecibirChat(inReader);
                hiloChat.start();
                System.out.println("Mensaje que quieres enviar a la sala, di bye si quieres salir: ");
                
                while(conectado){  
                    String mensaje = sc.nextLine();

                    if(mensaje.equals("bye")){
                        outPrinter.println("393");
                        conectado = false;
                        System.out.println("Has salido de la sala.");
                        hiloChat.parar();
                    }
                    else{
                        outPrinter.println("572|" + mensaje);
                    }
                }
            }
            socketServicio.close();

        }catch (UnknownHostException e) {
              System.err.println("Error: Nombre de host no encontrado.");
        }catch (IOException e) {
              System.err.println("Error de entrada/salida al abrir el socket.");
        }
    }
}
