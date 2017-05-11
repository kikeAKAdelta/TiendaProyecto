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
import javax.swing.JOptionPane;

/**
 *
 * @author oscar
 */
public class ControladorProducto {
    
    
    static Conexion cn;
    static ResultSet rs;
    private static boolean cambio;

    public static boolean isCambio() {
        return cambio;
    }

    public static void setCambio(boolean cambio) {
        ControladorProducto.cambio = cambio;
    }

   
   
    public static void Agregar(Producto pr) throws ErrorTienda{
        
        try {
            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO productos(CodBarra,Inventario,Costo,Nombre) VALUES('"+pr.getCodBarra()+"','"+pr.getInventario()+"','"+pr.getCosto()+"','"+pr.getNombre()+"')");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Agregar",e.getMessage());
        }
           
    }
    
    public static void Modificar(Producto pr) throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.execute("UPDATE productos SET Inventario='"+pr.getInventario()+"',Costo='"+pr.getCosto()+"',Nombre='"+pr.getNombre()+"' WHERE CodBarra='"+pr.getCodBarra()+"'");
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Modificar",e.getMessage());
        }
    }
    
    public static void Eliminar(Producto pr) throws ErrorTienda{
        try {
            
            String [] matriz=new String[4];
            String [] matriz2=new String[4];
            rs=cn.st.executeQuery("SELECT * FROM detallecompra WHERE CodBarra='"+pr.getCodBarra()+"'");
            
            while (rs.next()) {
                matriz[0]=rs.getString(1);
                matriz[1]=rs.getString(2);
                matriz[2]=rs.getString(3);
                matriz[3]=rs.getString(4);
                System.out.println(matriz[0]);
            }
            
            rs=cn.st.executeQuery("SELECT * FROM detalleventa WHERE CodBarra='"+pr.getCodBarra()+"'");
            
            while (rs.next()) {
                matriz2[0]=rs.getString(1);
                matriz2[1]=rs.getString(2);
                matriz2[2]=rs.getString(3);
                matriz2[3]=rs.getString(4);
                System.out.println(matriz2[1]);
            }
            
            if (matriz[0] != null || matriz2[1] != null) {
                setCambio(true);
            }else{
                cn.st.executeUpdate("DELETE FROM productos WHERE CodBarra='"+pr.getCodBarra()+"'");
                setCambio(false);
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Eliminar",e.getMessage());
        }
    }
    
    
    public static ArrayList<Producto> Buscar(String buscar) throws ErrorTienda{
        
        ArrayList<Object> producto = new ArrayList<Object>();
        
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT DISTINCT CodBarra,nombre,Inventario,Costo FROM productos WHERE nombre LIKE '%"+buscar+"%' OR CodBarra LIKE'%"+buscar+"%'");
            
                while (rs.next()) {
                    producto.add(rs.getString(1));
                    producto.add(rs.getString(2));
                    producto.add(rs.getString(3));
                    producto.add(rs.getString(4));
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
            rs=cn.st.executeQuery("SELECT CodBarra,nombre,Inventario,Costo FROM productos WHERE CodBarra='"+CodBarra+"'");
            while (rs.next()) {
                miproducto.setCodBarra(rs.getString(1));
                miproducto.setNombre(rs.getString(2));
                miproducto.setInventario(Integer.parseInt(rs.getString(3)));
                miproducto.setCosto(Double.parseDouble(rs.getString(4)));
            }
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProducto/Obtener",e.getMessage());
        }
        return miproducto;
    }
    
     
    
}
