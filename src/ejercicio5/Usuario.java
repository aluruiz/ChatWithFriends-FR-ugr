/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicio5;

import java.net.Socket;

/**
 *
 * @author Monica y Paula
 */
public class Usuario {
    String nombre;
    String password;
    String sala;
    Socket sesion; 

    Usuario(String usuario, String password){
        this.nombre = usuario;
        this.password = password;
    }
}
