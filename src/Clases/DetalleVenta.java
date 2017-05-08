/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.math.RoundingMode;
import java.sql.SQLException;
import java.text.DecimalFormat;

/**
 *
 * @author Jose Lopez
 */
public class DetalleVenta {
    
public Producto Producto;
public int Cantidad;
public double PrecioUnitario;

DecimalFormat decimal = new DecimalFormat("0.00");

    public DetalleVenta() {
    }

    
    
  public void New(String CodBarra, int Cantidad){
        this.Cantidad = Cantidad;
          
    }



    public double CalcularPrecio() throws SQLException, ClassNotFoundException, ErrorTienda{
        Parametro p=new Parametro();
        try{
           decimal.setRoundingMode(RoundingMode.CEILING); 
        return Double.parseDouble(decimal.format(Producto.getCosto()/(1-(Double.parseDouble(p.ObtenerUtilidad().getUtilidad())/100))));
        }catch(ArithmeticException ex){
            throw new ErrorTienda("class DetalleVenta/CalcularPrecio", ex.getMessage());
        }

    }

    public Producto getProducto() {
        return Producto;
    }

    public void setProducto(Producto Producto) {
        this.Producto = Producto;
    }

    public int getCantidad() {
        return Cantidad;
    }

    public void setCantidad(int Cantidad) {
        this.Cantidad = Cantidad;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(double PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }

    

}
