package ejercicio5;

import java.io.BufferedReader;
import java.io.IOException;

public class ThreadRecibirChat extends Thread {
    
    private final BufferedReader reader;
    private boolean running;
    
    public ThreadRecibirChat(BufferedReader reader) {
        this.reader = reader;
        this.running = true;
    }
    
    public void parar() {
        running = false;
        this.stop();
    }
    
    @Override
    public void run() {
        while (running) {
            try {
                String mensajeRecibido = reader.readLine();
                System.out.println(mensajeRecibido);
            } catch (IOException ex) {
                System.err.println("Error en el hilo lector del chat: " + ex.toString());
            }
        }
    }
}
