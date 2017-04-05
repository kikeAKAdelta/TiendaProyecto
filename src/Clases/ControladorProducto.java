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
            cn.st.executeUpdate("INSERT INTO productos(CodBarra,Inventario,Costo,Nombre) VALUES('"+pr.CodBarra+"','"+pr.Inventario+"','"+pr.Costo+"','"+pr.Nombre+"')");
        } catch (SQLException e) {
            throw new ErrorTienda("Ha escrito mal el comando DML hacia la BD");
        }
           
    }
    
    public static void Modificar(Producto pr) throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.executeUpdate("UPDATE productos SET Inventario='"+pr.Inventario+"',Costo='"+pr.Costo+"',Nombre='"+pr.Nombre+"' WHERE CodBarra='"+pr.CodBarra+"'");
        } catch (SQLException e) {
            throw new ErrorTienda("Ha escrito mal el comando DML hacia la BD");
        }
    }
    public static void Eliminar(Producto pr) throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.executeUpdate("DELETE FROM productos WHERE CodBarra='"+pr.CodBarra+"'");
        } catch (Exception e) {
            throw new ErrorTienda("Ha escrito mal el comando DML hacia la BD");
        }
    }
    
    public static ArrayList<Producto> Buscar(String buscar) throws ErrorTienda{
        
        ArrayList<Producto> producto = new ArrayList<Producto>();
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT CodBarra,nombre,Inevntario,Costo FROM productos WHERE nombre='"+buscar+"'");
        } catch (SQLException ex) {
            throw new ErrorTienda("Ha escrito mal el comando DML hacia la BD");
        }
        
        return producto;
    }
    public static Producto Obtener(String CodBarra) throws ErrorTienda{
        
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT CodBarra,nombre,Inevntario,Costo FROM productos WHERE CodBara='"+CodBarra+"'");
        } catch (SQLException ex) {
            throw new ErrorTienda("Ha escrito mal el comando DML hacia la BD");
        }
        return new Producto();
    }
    
}
