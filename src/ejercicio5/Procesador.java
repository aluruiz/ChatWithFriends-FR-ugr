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
import java.util.Random;
/**
 *
 * @author Monica y Paula
 */
public class Procesador extends Thread{

    /**
     * @param args the command line arguments
     */
     private Socket socketServicio;
     private InputStream inputStream;
     private OutputStream outputStream;

     public Procesador(Socket socketServicio){
       this.socketServicio = socketServicio;
     }

}
