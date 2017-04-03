/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.ControladorProducto.cn;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Compra {
    
public int IdCompra;
public Date Fecha;
public Proveedor PROVEEDOR;
public double Total;
public ArrayList<DetalleCompra> ARTICULOS;
        
        public void CalcularTotal() throws ErrorTienda {
          double total=0;
          try{
              for(DetalleCompra dc: this.ARTICULOS){
                  total=total+(dc.Cantidad*dc.CostoUnitario);
              }
              this.Total = Math.round(total*100.0)/100.0;
          }catch(ArithmeticException ex){
             
          }
        
        
        }
        
        public void AgregarItem(DetalleCompra dc) throws ErrorTienda{
            this.ARTICULOS.add(dc);
            this.CalcularTotal();
        }        
}
