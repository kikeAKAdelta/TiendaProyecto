/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import frame.JFRPrincipal;
import frame.frmNotificacion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Lopez
 */
public class ControladorVenta {
    
    
   
    static Conexion cn;
    static ResultSet rs;
    public PreparedStatement ps=null;
    JFRPrincipal form = new JFRPrincipal();
    
    public void mensajeNot(String mensaje) throws ErrorTienda{
        form.mensajeNotificacion(mensaje);
        form.limpiarVenta();
    }
    
    public static void Agregar(Venta vn,Object[][] detalles) throws ErrorTienda, SQLException{
        try {
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String Fecha = sdf.format(vn.getFecha());
            cn = new Conexion();
            cn.st.execute("INSERT INTO venta (IdVenta,Fecha,Cliente,Total) VALUES('"
                    +vn.getIdVenta()+"','"+Fecha+"','"+vn.getCliente()+"','"+Double.parseDouble(String.valueOf(vn.getTotal()))+"')");
            cn.conexion.close();
            ControladorVenta.ActualizarInventario(detalles); //actualiza el inventario
            

                cn = new Conexion();
              try {
                for(int x=0;x<detalles.length;x++){
                   cn.st.execute("INSERT INTO detalleventa(IdVenta,CodBarra,Cantidad,PrecioUnitario)VALUES('"
                           +detalles[x][0]+"','"+detalles[x][1]+"','"+detalles[x][2]+"','"+detalles[x][3]+"')");
                   
               }
                //JOptionPane.showMessageDialog(null, "Venta realizada exitosamente");
                  frmNotificacion not = new frmNotificacion();
                  not.mensaje("Venta realizada exitosamente");
                  
                  
            } catch (Exception e) {
                throw new ErrorTienda("ControladorVentas Agregar", e.getMessage());
            }
                
               
            
        } catch (SQLException e) {
            throw new ErrorTienda("ControladorVentas Agregar", e.getMessage());
        }finally{
            cn.conexion.close();
        }
       
    }
    
    public static int ObtenerIdVenta() throws ErrorTienda{
        int IdVenta=0;
        try {
            cn=new Conexion();
       rs=null;
        rs = cn.st.executeQuery("SELECT count(IdVenta) FROM venta");
        
        while(rs.next()){
            IdVenta = rs.getInt(1);
        }
        }catch (Exception ex){
            throw new ErrorTienda("Class ControladorVenta/ObtenerIdVenta", ex.getMessage());
        } 
        return IdVenta+1;
    }
    
    public static void ActualizarInventario(Object[][] detalles) throws ErrorTienda, SQLException{
        cn = new Conexion();
        try {
            Clases.Producto pr;
        for(int x =0;x<detalles.length;x++){
          
            pr = ControladorProducto.Obtener(String.valueOf(detalles[x][1]));
          int cantidad = pr.getInventario();
            
            int cantidad2= Integer.parseInt(String.valueOf(detalles[x][2]));
            
            System.out.println("Cantidad en la bd "+cantidad+" Cantidad exigida "+cantidad2);
            cn.st.execute("UPDATE productos SET Inventario='"+(cantidad-cantidad2)+"' WHERE CodBarra='"+detalles[x][1]+"'");
        }    
        } catch (SQLException e) {
        throw new ErrorTienda("Controlador Venta catualizar inventario", e.getMessage());
        }
        
        
    }

    
    
}