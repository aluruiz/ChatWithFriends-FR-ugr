package ejercicio4;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativo_4
// (CC) jjramos, 2012
//
public class YodafyServidorIterativo_4 {

	public static void main(String[] args) {
            
                DatagramSocket socketServidor;
		// Puerto de escucha
		int port=8989;
		
		try {
			// Mientras ... siempre!
			do {
				
				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
				socketServidor = new DatagramSocket(port);
                                //////////////////////////////////////////////////
				// Creamos un objeto de la clase ProcesadorYodafy_4, pasándole como 
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				ProcesadorYodafy_4 procesador=new ProcesadorYodafy_4(socketServidor);
				procesador.procesa();
                                
                                //Cerramos el socket
                                socketServidor.close();
				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
