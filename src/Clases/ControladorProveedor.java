/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.ControladorProducto.cn;
import static Clases.ControladorProducto.rs;
import static Clases.ControladorProducto.setCambio;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class ControladorProveedor {
    static Conexion cn;
    static ResultSet rs;
    private static boolean cambio;

    public static boolean isCambio() {
        return cambio;
    }

    public static void setCambio(boolean cambio) {
        ControladorProveedor.cambio = cambio;
    }
    
    
    public static void Agregar(Proveedor pv)throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO proveedor(IdProveedor,Nombre,Telefono,Direccion, NIT) VALUES('"+pv.getIdProveedor()+"','"+pv.getNombre()+"','"+pv.getTelefono()+"','"+pv.getDireccion()+"','"+pv.getNIT()+"')");
            
        } catch (SQLException ex) {
            throw new ErrorTienda("Class ControladorProveedor/Agregar", ex.getMessage());
        }
    }
    public static void Eliminar(Proveedor pv)throws ErrorTienda{
        try {
            cn.st.executeUpdate("DELETE FROM proveedor WHERE IdProveedor='"+pv.getIdProveedor()+"'");
        } catch (Exception e) {
            throw new ErrorTienda("Class ControladorProveedor/Eliminar", e.getMessage());
        }
    }
    public static void Modificar(Proveedor pv)throws ErrorTienda{
        try {
            String [] matriz=new String[4];
            rs=cn.st.executeQuery("SELECT * FROM compra WHERE CodBarra='"+pv.getIdProveedor()+"'");
            
            while (rs.next()) {
                matriz[0]=rs.getString(1);
                matriz[1]=rs.getString(2);
                matriz[2]=rs.getString(3);
                matriz[3]=rs.getString(4);
                
            }
            
            if (matriz[3] != null) {
                setCambio(true);
            }else{
                cn.st.executeUpdate("UPDATE proveedor SET Nombre='"+pv.getNombre()+"',Telefono='"+pv.getTelefono()+"',Direccion='"+pv.getDireccion()+"',NIT='"+pv.getNIT()+"' WHERE IdProveedor='"+pv.getIdProveedor()+"'");
                setCambio(false);
            }
            } catch (Exception e) {
            throw new ErrorTienda("Class ControladorProveedor/Modificar", e.getMessage());
        }
    }
    
    public static ArrayList<Proveedor> Buscar(String pv)throws ErrorTienda{
    ArrayList<Object> proveedor = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
            rs=cn.st.executeQuery("SELECT * FROM proveedor WHERE Nombre='"+pv+"'");
            
            while (rs.next()) {
                proveedor.add(rs.getString(1));
                proveedor.add(rs.getString(2));
                proveedor.add(rs.getString(3));
                proveedor.add(rs.getString(4));
                proveedor.add(rs.getString(5));
            }
            
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProveedor/Buscar",e.getMessage());
        }
        
        ArrayList<Proveedor> proveedores=(ArrayList) proveedor;
        return proveedores;
    }
    
    public static ArrayList<Proveedor> Obtener()throws ErrorTienda{
    ArrayList<Object> proveedor = new ArrayList<Object>();
    
            cn=new Conexion();   
            try { 
                rs=null;
            rs=cn.st.executeQuery("SELECT IdProveedor,Nombre,Telefono,Direccion, NIT FROM proveedor");
            while (rs.next()) {
 
                proveedor.add(rs.getString(1));
                proveedor.add(rs.getString(2));
                proveedor.add(rs.getString(3));
                proveedor.add(rs.getString(4));
                proveedor.add(rs.getString(5));
            }
            
        } catch (SQLException e) {
            throw new ErrorTienda("Class ControladorProveedor/Obtener",e.getMessage());
        }
        
        ArrayList<Proveedor> proveedores=(ArrayList) proveedor;
        return proveedores;
    }
    
    public static int ObtenerIdProveedor()throws ErrorTienda{
        int IdProveedor=0;   
        cn = new Conexion();
        try {
        rs = cn.st.executeQuery("SELECT MAX(IdProveedor) FROM proveedor");
        
            while(rs.next()){
                IdProveedor = rs.getInt(1);
            }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorProveedor/ObtenerIdProveedor", ex.getMessage());
        } 
        return IdProveedor;
    
    }
}
