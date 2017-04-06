/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
    public ArrayList<DetalleVenta> articulo;
    DecimalFormat decimal = new DecimalFormat("0.00");
    
    

    public Venta() {
        articulo = new ArrayList<DetalleVenta>();
    }

    public Venta(int IdVenta, Date Fecha, String Cliente, double Total, ArrayList<DetalleVenta> articulo) {
        this.IdVenta = IdVenta;
        this.Fecha = Fecha;
        this.Cliente = Cliente;
        this.Total = Total;
        this.articulo = articulo;
    }
    
   
    public void AgregarItem(DetalleVenta detalleVenta){   
        
        articulo=new ArrayList<DetalleVenta>();
        articulo.add(detalleVenta);
    }
    
    public double CalcularTotal() throws ErrorTienda {
        double total=0;
        try{
            for(DetalleVenta v: this.articulo){
            total=total+(Double.parseDouble(v.getCantidad())*(v.getPrecioUnitario()));
        }
        this.Total=total;
        decimal.setRoundingMode(RoundingMode.CEILING);
        return Double.parseDouble(decimal.format(total));
        }catch(ArithmeticException ex){
            throw new ErrorTienda("Error de Calculo", ex.getMessage());
        }
        
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public ArrayList<DetalleVenta> getArticulo() {
        return articulo;
    }

    public void setArticulo(ArrayList<DetalleVenta> articulo) {
        this.articulo = articulo;
    }
            
}
