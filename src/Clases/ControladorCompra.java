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
 * @author pedro
 */
public class ControladorCompra {
    
     
             
    public static void Agregar(Compra cm) throws ErrorTienda{
    
     try {
            cn.st.executeUpdate("INSERT INTO compra(IdCompra,Fecha,IdProveedor,Total) VALUES('"+cm.IdCompra+"','"+cm.Fecha+"','"+cm.PROVEEDOR.IdProveedor+"','"+cm.Total+"')");
        } catch (SQLException ex) {
            throw new ErrorTienda("Class ControladorCompra/Agregar", ex.getMessage());
        }
    
    }
    public static void ActualizarInventario(Compra cm) throws ErrorTienda {
        //Obtener la cantidad actual del producto
        int CantidadActual=0;
        try {
        ResultSet rsCantidad = null;
        rsCantidad = cn.st.executeQuery("SELECT Cantidad FROM productos WHERE CodBarra='"+cm.ARTICULOS.get(0).PRODUCTO.CodBarra+"'");
        
        while(rsCantidad.next()){
            CantidadActual = rsCantidad.getInt("Cantidad");
        }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorCompra/ActualizarInventario", ex.getMessage());
        }
        
        //Actualizamos el inventario 
                try {
            cn.st.executeUpdate("UPDATE productos SET Inventario='"+(CantidadActual+cm.ARTICULOS.get(0).Cantidad)+"' WHERE CodBarra='"+cm.ARTICULOS.get(0).PRODUCTO.CodBarra+"'");
        } catch (Exception ex) {
            throw new ErrorTienda("Class ControladorCompra/ActualizarInventario", ex.getMessage());
        }
         
    }
    public static void ActualizarPrecioPromedioProducto(ArrayList<DetalleCompra> detalleCompra) throws ErrorTienda {
         //Obtener la cantidad actual del producto
        int CantidadActual=0;
        try {
        ResultSet rsCantidad = null;
        rsCantidad = cn.st.executeQuery("SELECT Cantidad FROM productos WHERE CodBarra='"+detalleCompra.get(0).PRODUCTO.CodBarra);
        
        while(rsCantidad.next()){
            CantidadActual = rsCantidad.getInt("Cantidad");
        }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorCompra/ActualizarPrecioPromedioProducto", ex.getMessage());
        }
       
        //Obtener el precio actual
        double PrecioActual=0;
        try {
        ResultSet rsPrecio = null;
        rsPrecio = cn.st.executeQuery("SELECT Costo FROM productos WHERE CodBarra='"+detalleCompra.get(0).PRODUCTO.CodBarra);
        
        while(rsPrecio.next()){
            PrecioActual = rsPrecio.getDouble("Costo");
        }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorCompra/ActualizarPrecioPromedioProducto", ex.getMessage());
        }
        
        //Actualizamos el precio promedio
         try {
            cn.st.executeUpdate("UPDATE productos SET Costo='"+((CantidadActual*PrecioActual)+((detalleCompra.get(0).Cantidad)*(detalleCompra.get(0).CostoUnitario)))+"'");
        } catch (Exception ex) {
            throw new ErrorTienda("Class ControladorCompra/ActualizarPrecioPromedioProducto", ex.getMessage());
        }
        
//        cantidadactual*precioactual(25*0.30) + cantidadnuevacomprada*precionuevo(10*0.36)/CantidadTotal = precio ponderado del producto.
    }
    public static int ObtenerIdCompra() throws ErrorTienda {
       cn =new Conexion();
        int IdCompra=0;
        try {
        ResultSet rsIdCompra = null;
        rsIdCompra = cn.st.executeQuery("SELECT COUNT(*) FROM compra");
        
        while(rsIdCompra.next()){
            IdCompra = rsIdCompra.getInt("count(*)");
        }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorCompra/ObtenerIdCompra", ex.getMessage());
        } 
        return IdCompra;
    }
    
}
