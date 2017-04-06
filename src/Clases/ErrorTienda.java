/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author dell
 */
public class ErrorTienda extends Exception {
    
    public ErrorTienda(String mensajeError){
        super(mensajeError);
        JOptionPane.showMessageDialog(null, mensajeError);
    }

    ErrorTienda(String error, String ruta_de_error, String messageException) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
