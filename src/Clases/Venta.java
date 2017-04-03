/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author oscar
 */
public class Venta {
    
    public int IdVenta;
    public Date Fecha;
    public String Cliente;
    public double Total;
    public ArrayList<DetalleVenta> ARTICULO;
    
   
    public void AgregarItem(DetalleVenta detalleVenta){   
        
        ARTICULO=new ArrayList<DetalleVenta>();
        
        ARTICULO.add(detalleVenta);
    }
    
    public void CalcularTotal(){
    
    }
            
}
