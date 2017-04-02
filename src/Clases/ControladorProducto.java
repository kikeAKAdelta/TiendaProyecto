/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class ControladorProducto {
    
    static Conexion cn=new Conexion();
    
    public static void Agregar(Producto pr){
        try {
            cn.st.executeUpdate("INSERT INTO productos(CodBarra,Inventario,Costo,Nombre) VALUES('"+pr.CodBarra+"','"+pr.Inventario+"','"+pr.Costo+"','"+pr.Nombre+"')");
            cn.conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public static void Modificar(Producto pr){
        try {
            cn.st.executeUpdate("UPDATE productos SET Inventario='"+pr.Inventario+"',Costo='"+pr.Costo+"',Nombre='"+pr.Nombre+"' WHERE CodBarra='"+pr.CodBarra+"'");
        } catch (Exception e) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public static void Eliminar(Producto pr){
        try {
            cn.st.executeUpdate("DELETE FROM productos WHERE CodBarra='"+pr.CodBarra+"'");
        } catch (Exception e) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static ArrayList<Producto> Buscar(String buscar){
        
        ArrayList<Producto> producto = new ArrayList<Producto>();
        return producto;
    }
    public static Producto Obtener(String CodBarra){
        return new Producto();
    }
    
}
