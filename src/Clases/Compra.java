/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.ControladorProducto.cn;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Compra {
    
public int IdCompra;
public String Fecha;
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
           throw new ErrorTienda("Class Compra/CalcularTotal", ex.getMessage());  
          }
        }
        
        public void AgregarItem(DetalleCompra dc) throws ErrorTienda{
            try{
                this.ARTICULOS.add(dc);
                this.CalcularTotal();
            }catch(Exception ex) {
                throw new ErrorTienda("Class Compra/AgregarItem", ex.getMessage());
            }
           
        }  

    public int getIdCompra() {
        return IdCompra;
    }

    public void setIdCompra(int IdCompra) {
        this.IdCompra = IdCompra;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String Fecha) {
        this.Fecha = Fecha;
    }

    public Proveedor getPROVEEDOR() {
        return PROVEEDOR;
    }

    public void setPROVEEDOR(Proveedor PROVEEDOR) {
        this.PROVEEDOR = PROVEEDOR;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public ArrayList<DetalleCompra> getARTICULOS() {
        return ARTICULOS;
    }

    public void setARTICULOS(ArrayList<DetalleCompra> ARTICULOS) {
        this.ARTICULOS = ARTICULOS;
    }
        
        
}
