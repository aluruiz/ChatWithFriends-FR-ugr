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
    Socket socket;
    int port = 8989;
    String host = "localhost";
    
    public static void escoger(int numero){
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter outPrinter = new PrintWriter(outputStream,true);
        outPrinter.println(numero);
    }
    
  public static void main(String[] args) {
      byte []buferEnvio;
      byte []buferRecepcion=new byte[256];
      int bytesLeidos=0;
      
      try{
        Scanner sc = new Scanner(System.in);
        int numero = sc.nextInt();
        escoger(numero);
    } catch (UnknownHostException e) {
        System.err.println("Error: Nombre de host no encontrado.");
    } catch (IOException e) {
        System.err.println("Error de entrada/salida al abrir el socket.");
    }
  }  
}
