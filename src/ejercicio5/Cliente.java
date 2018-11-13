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
 * @author Monica
 */
public class Cliente {
    //Codigos:
    private static String IDE = "025"; //Codigo de identificacion
    private static String OK = "417"; 
    private static String ERROR = "427";
    
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

        try{
            //Creamos un socket que se conecte con host y port
            socketServicio = new Socket(host,port);

            InputStream inputStream = socketServicio.getInputStream();
            OutputStream outputStream = socketServicio.getOutputStream();

            //Identificarse: 
            //Insertar [CODIGO][DELIMITADOR][USUARIO][DELIMITADOR][CONTRASEÑA]:
            System.out.println("Codigos: \n 025 - Identificarse como usuario.\n 133 - Crear un nuevo usuario.");
            System.out.println("Inserte Usuario y Contraseña de la siguiente forma: [CODIGO][DELIMITADOR][USUARIO][DELIMITADOR = |][CONTRASEÑA]");
            String identificarse = sc.nextLine();
            
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
            }
            
            if(puede_seguir){
                System.out.println("A que sala quieres conectarte:\n 037 - Sala Celeste\n 039 - Sala Azafran\n 216 - Sala Lavacaldaªn");
                String sala = sc.nextLine();
                BufferedReader inRd = new BufferedReader(new InputStreamReader(inputStream));
                String salaElegida = inRd.readLine();
                System.out.println("Array recibido: " + salaElegida);
            }
            
        }catch (UnknownHostException e) {
              System.err.println("Error: Nombre de host no encontrado.");
        }catch (IOException e) {
              System.err.println("Error de entrada/salida al abrir el socket.");
        }
    }  
}
