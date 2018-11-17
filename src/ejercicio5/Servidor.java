/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;
import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Monica y Paula
 */
public class Servidor {
    //Base de Datos de Usuarios
    public ArrayList<Usuario> usuarios = new ArrayList();
    
    //Array de Conectados
    public ArrayList<Usuario> conectados = new ArrayList();
    
    //Array de Salas
    public ArrayList<Usuario> celeste = new ArrayList();
    public ArrayList<Usuario> azafran = new ArrayList();
    public ArrayList<Usuario> lavacalda = new ArrayList();
    public static void main(String[] args) {
        new Servidor();
    }
    
    public Servidor(){
        // Puerto de escucha
		int port=8989;

                inicializarUsuarios();
		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"                       
                        
                        //////////////////////////////////////////////////
			ServerSocket socketServidor = new ServerSocket(port);
			Socket socketServicio = null; 
                        ////////////////////////////////////////////////// 
			// Mientras ... siempre!
			do {
				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
                                    socketServicio = socketServidor.accept(); 
                                    //////////////////////////////////////////////////
                                    // Creamos un objeto de la clase Hebrita, pasándole como 
                                    // argumento el nuevo socket, para que realice el procesamiento
                                    // Este esquema permite que se puedan usar hebras más fácilmente.
                                    Procesador procesador=new Procesador(socketServicio, this);
                                    System.out.println("Se ha recibido una petición");
                                    procesador.start();
                                
				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}
    }
    
    private void inicializarUsuarios(){
        usuarios.add(new Usuario("ashketchum", "paleta"));
        usuarios.add(new Usuario("teamrocket", "despegadenuevo"));
        usuarios.add(new Usuario("oak","profesorguay"));
        usuarios.add(new Usuario("misty","liderceleste"));
        usuarios.add(new Usuario("brock","supercriador"));
        usuarios.add(new Usuario("maya","hojaverdecity"));
        usuarios.add(new Usuario("iris","teseliablanco"));
    }

}
