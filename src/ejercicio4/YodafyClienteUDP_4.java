package ejercicio4;
//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class YodafyClienteUDP_4 {

	public static void main(String[] args) {
                        
		// Nombre del host donde se ejecuta el servidor:
		String host="localhost";
		// Puerto en el que espera el servidor:
		int port=8989;
		
		// Socket para la conexión TCP
		InetAddress direccion; 
                DatagramPacket paquete;
                byte []buferEnvio;
                byte []buferRecepcion = new byte[256];
                DatagramSocket socket;
		
		try {
			// Creamos un socket que se conecte a "hist" y "port":
			//////////////////////////////////////////////////////
			socket = new DatagramSocket();
			//////////////////////////////////////////////////////			
			// Si queremos enviar una cadena de caracteres por un OutputStream, hay que pasarla primero
			// a un array de bytes:
			buferEnvio = "Al monte del volcán debes ir sin demora".getBytes();
			
                        // Enviamos el array:
			//////////////////////////////////////////////////////
                        direccion = InetAddress.getByName(host);
                        paquete = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
			socket.send(paquete);
			//////////////////////////////////////////////////////
                        
			// Leemos la respuesta del servidor. Para ello le pasamos un array de bytes, que intentará
			// rellenar. El método "read(...)" devolverá el número de bytes leídos.
			//////////////////////////////////////////////////////
                        paquete = new DatagramPacket(buferRecepcion, buferRecepcion.length);
                        socket.receive(paquete);
                        //////////////////////////////////////////////////////
			
			// Mostremos la cadena de caracteres recibidos:
			String caracteres = new String(buferRecepcion);
                        System.out.println(caracteres);
			
			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			socket.close();
			//////////////////////////////////////////////////////
			
			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
