/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.ControladorProducto.cn;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author pedro
 */
public class ControladorCompra {
    static Conexion cn;
    static DecimalFormat decimal = new DecimalFormat("0.00");
     
             
    public static void Agregar(Compra cm, Object[][] detalleCompra) throws ErrorTienda, SQLException{
    
     try {
            cn.st.executeUpdate("INSERT INTO compra(IdCompra,Fecha,IdProveedor,Total) VALUES('"+cm.IdCompra+"','"+cm.getFecha()+"','"+cm.PROVEEDOR.IdProveedor+"','"+cm.Total+"')");
            cn.conexion.close();
            ControladorCompra.ActualizarInventario(detalleCompra);
            
            cn = new Conexion();
              try {
                for(int x=0;x<detalleCompra.length;x++){
                   cn.st.execute("INSERT INTO detallecompra(CodBarra,IdCompra,Cantidad,CostoUnitario)VALUES('"
                           +detalleCompra[x][0]+"','"+detalleCompra[x][1]+"','"+detalleCompra[x][2]+"','"+detalleCompra[x][3]+"')");
                   
               }
                
            } catch (Exception e) {
                throw new ErrorTienda("ControladorCompra Agregar", e.getMessage());
            }
            
     } catch (SQLException ex) {
            throw new ErrorTienda("Class ControladorCompra/Agregar", ex.getMessage());
        }finally{
            cn.conexion.close();
        }
    
    }
    
    public static void ActualizarInventario(Object[][] detalleCompra) throws ErrorTienda {
        cn = new Conexion();
        try {
            Clases.Producto pr;
        for(int x =0;x<detalleCompra.length;x++){
            pr = ControladorProducto.Obtener(String.valueOf(detalleCompra[x][0]));
            int cantidad = pr.getInventario();
            int cantidad2= Integer.parseInt(String.valueOf(detalleCompra[x][2]));
            
            System.out.println("Cantidad en la bd "+cantidad+" Cantidad exigida "+cantidad2);
            cn.st.execute("UPDATE productos SET Inventario='"+(cantidad+cantidad2)+"' WHERE CodBarra='"+detalleCompra[x][0]+"'");
        }    
        } catch (SQLException e) {
        throw new ErrorTienda("Controlador Compra actualizar inventario", e.getMessage());
        }
    }
    
    
    public static void ActualizarPrecioPromedioProducto(ArrayList<DetalleCompra> detalleCompra) throws ErrorTienda {
         //Obtener la cantidad actual del producto
        int CantidadActual=0;
        try {
            for (int i = 0; i < detalleCompra.size(); i++) {
                
            
                ResultSet rsCantidad = null;
                rsCantidad = cn.st.executeQuery("SELECT Inventario FROM productos WHERE CodBarra='"+detalleCompra.get(i).PRODUCTO.getCodBarra()+"'");

                while(rsCantidad.next()){
                    CantidadActual = rsCantidad.getInt(1);
                }

                //Obtener el precio actual
                double PrecioActual=0;

                ResultSet rsPrecio = null;
                rsPrecio = cn.st.executeQuery("SELECT Costo FROM productos WHERE CodBarra='"+detalleCompra.get(i).PRODUCTO.getCodBarra()+"'");

                while(rsPrecio.next()){
                    PrecioActual = rsPrecio.getDouble(1);


                }
                double actualizarPrecio = ((((CantidadActual)*(PrecioActual))+((detalleCompra.get(i).getCantidad())*(detalleCompra.get(i).getCostoUnitario())))/((detalleCompra.get(i).getCantidad())+CantidadActual));
                
                cn.st.executeUpdate("UPDATE productos SET Costo='"+decimal.format(actualizarPrecio)+"' WHERE CodBarra='"+detalleCompra.get(i).PRODUCTO.getCodBarra()+"'");
            }
        
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorCompra/ActualizarPrecioPromedioProducto", ex.getMessage());
        }
        
        //Actualizamos el precio promedio
         
        
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
