/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class ErrorTienda extends Exception {
    
    public void New(String mensajeError){
        System.out.println(mensajeError);
    }
}
