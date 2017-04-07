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

/**
 *
 * @author Jose Lopez
 */
public class ControladorVenta {
    
    
   
    
    
    public static void Agregar(Venta vn) throws ErrorTienda, SQLException{
        try {
            cn.st.execute("INSERT INTO venta(IdCompra,Fecha,Cliente,Total) VALUES('"+vn.IdVenta+"','"+vn.Fecha+"','"+vn.Cliente+"','"+vn.Total+"')");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorVenta/Agregar", e.getMessage());
        }

    }
    
    public static int ObtenerIdVenta() throws ErrorTienda{
        int IdVenta=0;
        try {
        ResultSet rsVenta = null;
        rsVenta = cn.st.executeQuery("SELECT MAX(IdVenta) FROM detalleventa");
        
        while(rsVenta.next()){
            IdVenta = rsVenta.getInt("IdVenta");
        }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorCompra/ObtenerIdVenta", ex.getMessage());
        } 
        return IdVenta;
    }
    
    public static void ActualizarInventario(ArrayList<DetalleVenta> detalleVenta) throws ErrorTienda{
        int CantidadActual=0;
        try {
        ResultSet rsCantidad;
        rsCantidad = cn.st.executeQuery("SELECT Cantidad FROM productos WHERE CodBarra='"+detalleVenta.get(0).toString()+"'");
        
        while(rsCantidad.next()){
            CantidadActual = rsCantidad.getInt("Cantidad");
        }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorVenta/ActualizarInventario", ex.getMessage());
        }
        
        try {
            
            int descontar = Integer.parseInt(detalleVenta.get(1).toString());
            cn.st.executeUpdate("UPDATE productos SET Inventario='"+(CantidadActual-descontar)+"' WHERE CodBarra='"+detalleVenta.get(0)+"'");
        } catch (Exception ex) {
            throw new ErrorTienda("Class ControladorVenta/ActualizarInventario", ex.getMessage());
        }
        
    }

    
    
}