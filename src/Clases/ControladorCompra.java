/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.ControladorProducto.cn;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class ControladorCompra {
    
    public static void Agregar(Compra cm){
    
     try {
            cn.st.executeUpdate("INSERT INTO compra(IdCompra,Fecha,IdProveedor,Total) VALUES('"+cm.IdCompra+"','"+cm.Fecha+"','"+cm.PROVEEDOR.IdProveedor+"','"+cm.Total+"')");
        } catch (SQLException ex) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public static void ActualizarInventario(DetalleCompra dc) {
        
         try {
            cn.st.executeUpdate("UPDATE productos SET Inventario='"+(dc.PRODUCTO.Inventario+dc.Cantidad)+"' WHERE CodBarra='"+dc.PRODUCTO.CodBarra+"'");
        } catch (Exception e) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, e);
        }
         
    }
    public static void ActualizarPrecioPromedioProducto(ArrayList<DetalleCompra> detalleCompra) {

        
    }
    public static int ObtenerIdCompra() {
        int IdCompra=0;
        return IdCompra;
    }
    
}
