/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class ControladorProducto {
    
    
    static Conexion cn;
    static ResultSet rs;
   
    public static void Agregar(Producto pr) throws ErrorTienda{
        
        try {
            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO productos(CodBarra,Inventario,Costo,Nombre) VALUES('"+pr.getCodBarra()+"','"+pr.getInventario()+"','"+pr.getCosto()+"','"+pr.getNombre()+"')");
        } catch (SQLException e) {
            throw new ErrorTienda("",e.getMessage());
        }
           
    }
    
    public static void Modificar(Producto pr) throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.executeUpdate("UPDATE productos SET Inventario='"+pr.getInventario()+"',Costo='"+pr.getCosto()+"',Nombre='"+pr.getNombre()+"' WHERE CodBarra='"+pr.getCodBarra()+"'");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Modificar",e.getMessage());
        }
    }
    public static void Eliminar(Producto pr) throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.executeUpdate("DELETE FROM productos WHERE CodBarra='"+pr.getCodBarra()+"'");
        } catch (Exception e) {
            throw new ErrorTienda("Class ControladorProducto/Eliminar",e.getMessage());
        }
    }
    
    public static ArrayList<Producto> Buscar(String buscar) throws ErrorTienda{
        
        ArrayList<Object> producto = new ArrayList<Object>();
        
        
        
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT CodBarra,nombre,Inventario,Costo FROM productos WHERE nombre='"+buscar+"'");
            
            while (rs.next()) {
                producto.add(rs.getString(0));
                producto.add(rs.getString(1));
                producto.add(rs.getString(2));
                producto.add(rs.getString(3));
            }
            
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Buscar",e.getMessage());
        }
        
        ArrayList<Producto> productos=(ArrayList) producto;
        
        return productos;
    }
    public static Producto Obtener(String CodBarra) throws ErrorTienda{
        Producto miproducto=new Producto();
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT CodBarra,nombre,Inevntario,Costo FROM productos WHERE CodBara='"+CodBarra+"'");
            while (rs.next()) {
                miproducto.setCodBarra(rs.getString(0));
                miproducto.setNombre(rs.getString(1));
                miproducto.setInventario(Integer.parseInt(rs.getString(2)));
                miproducto.setCosto(Double.parseDouble(rs.getString(3)));
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Obtener",e.getMessage());
        }
        return miproducto;
    }
    
}
