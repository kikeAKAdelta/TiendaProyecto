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
 * @author oscar
 */
public class ControladorProveedor {
    static Conexion cn;
    
    public static void Agregar(Proveedor pv)throws ErrorTienda{
        try {
            cn=new Conexion();
            cn.st.executeUpdate("INSERT INTO proveedor(IdProveedor,Nombre,Telefono,Direccion, NIT) VALUES('"+pv.IdProveedor+"','"+pv.Nombre+"','"+pv.Telefono+"','"+pv.Direccion+"','"+pv.NIT+"')");
            
        } catch (SQLException ex) {
            throw new ErrorTienda("Ha escrito mal el comando DML hacia la BD");
        }
    }
    public static void Eliminar(Proveedor pv){
        try {
            cn.st.executeUpdate("DELETE FROM proveedor WHERE IdProveedor='"+pv.IdProveedor+"'");
        } catch (Exception e) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public static void Modificar(Proveedor pv){
        try {
            cn.st.executeUpdate("UPDATE proveedor SET Nombre='"+pv.Nombre+"',Telefono='"+pv.Telefono+"',Direccion='"+pv.Direccion+"',NIT='"+pv.NIT+"' WHERE IdProveedor='"+pv.IdProveedor+"'");
        } catch (Exception e) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public static ArrayList<Proveedor> Buscar(String pv){
    ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();
    return proveedor;
    }
    public static ArrayList<Proveedor> Obtener(){
        ArrayList<Proveedor> proveedor = new ArrayList<Proveedor>();
        return proveedor;
    }
    public static int ObtenerIdProveedor(){
        int IdProveedor=0;    
        return IdProveedor;
    }
}
