/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author dell
 */
public class Compra {
    
public int IdCompra;
public Date Fecha;
public Proveedor PROVEEDOR;
public double Total;
public DetalleCompra[] ARTICULOS;
        
        public void CalcularTotal() {}
        public void AgregarItem(DetalleCompra dc){}        
}
