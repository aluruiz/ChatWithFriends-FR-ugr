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

/**
 *
 * @author Monica
 */
public class Servidor {

  public static int ImprimirMenuEleccion(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Menu de Eleccion: ");
    System.out.println("Selecciona 1: En caso de comunicarte con Amigo.");
    System.out.println("Selecciona 2: En caso de añadir Amigo.");
    int numero;
    
    return numero;
  }

  public static boolean Identificarse(){
    boolean esta_identificado = false;
    return esta_identificado;
  }

  public static void MenuEleccion(int eleccion){
    switch (eleccion) {
      case 1:
      //Comunicarte con un amigo
      ;
      case 2:
      //Añadir un Amigo
      ;
      default:
        System.out.println(" Esta opcion no es válida. ")
        ;
    }
  }

  public static void AñadirAmigo(){
      
  }

  public static void Comunicarse(){

  }

  public static void main(String[] args) {
    int eleccion;
    eleccion = ImprimirMenuEleccion();
    MenuEleccion(eleccion);

  }

}
