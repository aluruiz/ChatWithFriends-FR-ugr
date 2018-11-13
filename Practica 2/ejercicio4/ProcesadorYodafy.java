package servidor.ejercicio4;

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
import java.util.Random;


//
// Nota: si esta clase extendiera la clase Thread, y el procesamiento lo hiciera el método "run()",
// ¡Podríamos realizar un procesado concurrente! 
//
public class ProcesadorYodafy {
	DatagramSocket socketServicio;
        DatagramPacket paquete; 
        byte []buferRecepcion = new byte[256];
        byte []buferEnvio;
        InetAddress direccion;
        int port; 
	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;
        
	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public ProcesadorYodafy(DatagramSocket socketServicio) {
		this.socketServicio=socketServicio;
		random=new Random();
	}
	
	
	// Aquí es donde se realiza el procesamiento realmente:
	void procesa(){
		try {
			// Lee la frase a Yodaficar:
			////////////////////////////////////////////////////////
                        paquete = new DatagramPacket(buferRecepcion, buferRecepcion.length);
			socketServicio.receive(paquete);
                        direccion = paquete.getAddress();
                        port = paquete.getPort();
                        ////////////////////////////////////////////////////////
			
			// Yoda hace su magia:
			// Creamos un String a partir de un array de bytes de tamaño "bytesRecibidos":
			String peticion=new String(buferRecepcion,0,buferRecepcion.length);
                        // Yoda reinterpreta el mensaje:
			String respuesta=yodaDo(peticion);
			// Convertimos el String de respuesta en una array de bytes:
			buferEnvio=respuesta.getBytes();
			                 
			// Enviamos la traducción de Yoda:
			////////////////////////////////////////////////////////
			paquete = new DatagramPacket(buferEnvio,buferEnvio.length,direccion,port);
                        socketServicio.send(paquete);
                        ////////////////////////////////////////////////////////
			
		} catch (IOException e) {
			System.err.println("Error al obtener los flujos de entrada/salida.");
		}

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
            System.out.println("Pido: " + peticion);
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";
		
		for(int i=0; i<s.length; i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];
			
			s[j]=s[k];
			s[k]=tmp;
		}
		
		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}
                   
                //resultado = resultado.replaceAll(System.getProperty("line.separator"), "").concat(System.getProperty("line.separator")); //eliminar salto de linea que no se por qué sale
                System.out.println("Transformado es " + resultado);
		return resultado;
	}
}
