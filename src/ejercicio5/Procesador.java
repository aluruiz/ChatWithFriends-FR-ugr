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
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Monica y Paula
 */
public class Procesador extends Thread{
    //Referencia a un socket para enviar/recibir las peticiones/respuestas
    private Socket socketServicio;
    //stream de lectura
    private InputStream inputStream;
    //stream de escritura
    private OutputStream outputStream;
            
    //Base de Datos de Usuarios
    ArrayList<Usuario> usuarios = new ArrayList();
    
    //Constructor que tiene como parámetro una referencia al socket abierto por otra clase.
    public Procesador(Socket socketServicio){
      this.socketServicio = socketServicio;
    }
    
    //Aquí es donde se realiza el procesamiento realmente:
    @Override
    public void run(){
        try{
            //obtiene los flujos de escritura/lectura
            inputStream=socketServicio.getInputStream();
            outputStream=socketServicio.getOutputStream();
           
            //Identificarse:
            //Lee el usuario y la contraseña:
            BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
            String identificacion = inReader.readLine();
            System.out.println("A separar: " + identificacion);
            //Dividimos el usuario y contraseña en partes
            String[] parts= identificacion.split("\\|");
            String codigo = parts[0];
            String usuario = parts[1];
            String contrasenia = parts[2];
            System.out.println("Separado: Codigo=" + codigo + " Usuario=" + usuario + " Contraseña=" + contrasenia );
            String autentificacion;
            
            //Variable de envio
            PrintWriter outPrinter = new PrintWriter(outputStream, true);
            switch (codigo){
                //Comprobar si el usuario esta en el sistema.
                case "025": //pikachu
                    autentificacion = iniciarSesion(usuario, contrasenia);
                    System.out.println("Se ha intentado inciar sesion. Codigo producido=" + autentificacion);
                    //Enviamos el array de autentificacion
                    outPrinter.println(autentificacion);
                    System.out.println("Se ha enviado el codigo de iniciar sesion.");
                //Darse de alta en el sistema.
                case "133": //eevee
                    autentificacion = crearUsuario(usuario, contrasenia);
                    System.out.println("Se ha intentado crear Usuario. Codigo producido=" + autentificacion);
                    //Enviamos el array de autentificacion
                    outPrinter.println(autentificacion);
                    System.out.println("Se ha enviado el codigo de crear usuario.");
            }

        }catch (UnknownHostException e) {
              System.err.println("Error: Nombre de host no encontrado.");
        }catch (IOException e) {
              System.err.println("Error de entrada/salida al abrir el socket.");
        }
    }
    
    String iniciarSesion(String user, String pass) {
        System.out.println("Ha entrado en inciar sesion.");
        for (Usuario users : usuarios) {
            if (users.nombre.equals(user) && users.password.equals(pass)){
                System.out.println("Ha salido bien de iniciar sesion");
                return "417"; // inicio de sesion correcto.
            }
        }
        //si no lo ha encontrado
        System.out.println("Va ha salir mal de iniciar sesion");
        return "427";
    }
    
    String crearUsuario (String user, String pass){
        System.out.println("Ha entrado en crear usuario.");
        for (Usuario users : usuarios) {
            if (users.nombre.equals(user)){
                System.out.println("Va ha salir mal de crear usuario");
                return "155"; // nombre de usuario cogido
            }
        }
        usuarios.add(new Usuario(user,pass));
        System.out.println("Ha salido bien de crear usuario");
        return "152"; //se ha creado el nuevo usuario
    }

}
