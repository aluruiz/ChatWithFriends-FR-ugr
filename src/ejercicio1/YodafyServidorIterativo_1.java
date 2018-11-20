package ejercicio1;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorIterativo_1 {

	public static void main(String[] args) {
            
                ServerSocket socketServidor;
		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		byte []buffer=new byte[256];
		// Número de bytes leídos
		int bytesLeidos=0;
                Socket socketServicio = null;
		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"                       
                        
                        //////////////////////////////////////////////////
			socketServidor = new ServerSocket(port); 
			//////////////////////////////////////////////////
			
			// Mientras ... siempre!
			do {
				
				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
				socketServicio = socketServidor.accept(); 
				//////////////////////////////////////////////////
				// Creamos un objeto de la clase ProcesadorYodafy, pasándole como 
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				ProcesadorYodafy_1 procesador=new ProcesadorYodafy_1(socketServicio);
				procesador.procesa();
				
			} while (true);
			
		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
