/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.ControladorProducto.cn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jose Lopez
 */
public class ControladorVenta {
    
    
    Conexion cn;
    ResultSet rs;
    
    public static void Agregar(Venta vn){
        
    }
    
    public static int ObtenerIdVenta() throws ErrorTienda{
        int IdVenta=0; 
        
        return IdVenta;
    }
    
    public static void ActualizarInventario(ArrayList<DetalleVenta> detalleVenta){
        
    }

    
    
}