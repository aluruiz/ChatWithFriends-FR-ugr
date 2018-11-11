package servidor.ejercicio3;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorConcurrente
// (CC) jjramos, 2012
//
public class YodafyServidorConcurrente {

	public static void main(String[] args) {

		// Puerto de escucha
		int port=8989;

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
                                    Hebrita procesador=new Hebrita(socketServicio);
                                    System.out.println("Se ha recibido una petición");
                                    procesador.start();
                                
				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
