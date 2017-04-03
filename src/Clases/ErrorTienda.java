/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author dell
 */
public class ErrorTienda extends Exception {
    
    public ErrorTienda(){}
    
    public void New(String mensajeError){
        System.out.println("Ha ocurrido un error en el programa");
    }
}
