/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.ControladorProducto.cn;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author Jose Lopez
 */
public class ControladorVenta {
    
    
   
    static Conexion cn;
    static ResultSet rs;
    public PreparedStatement ps=null;
    
    public static void Agregar(Venta vn) throws ErrorTienda{
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
            String Fecha = sdf.format(vn.getFecha());
            try{
                
                cn = new Conexion();
                cn.st.execute("INSERT INTO venta (IdVenta,Fecha,Cliente,Total) VALUES('"+vn.getIdVenta()+"','"+Fecha+"','"+vn.getCliente()+"','"+vn.getTotal()+"')");
//                cn.conexion.close();
                
                ControladorVenta.ActualizarInventario(vn.getArticulo()); //actualiza el inventario
                
                for(DetalleVenta d: vn.getArticulo()){  //inserta el detalle venta de cada detalle
                    
                    JOptionPane.showMessageDialog(null, "Total articulos "+vn.getArticulo().size());
                    PreparedStatement consulta2;
                    consulta2 = cn.conexion.prepareStatement("INSERT INTO detalleventa (IdVenta, CodBarra, Cantidad, PrecioUnitario) VALUES (?, ?, ?, ?)");
                    
                    consulta2.setInt(1, vn.getIdVenta());
                    consulta2.setString(2, vn.getArticulo().get(0).getProducto().getCodBarra());
                    consulta2.setInt(3, vn.getArticulo().get(0).getCantidad());
                    
                    DecimalFormat df = new DecimalFormat("#.##");
                    df.setRoundingMode(RoundingMode.CEILING);
                    consulta2.setDouble(4, Double.parseDouble(df.format(767676)));
                    consulta2.executeUpdate();
            }
            
        }catch(SQLException ex){
            throw new ErrorTienda("Error SQL, ControladorVenta.Agregar", ex.getMessage());
        }finally{
          //cn.conexion.close();
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
    
    public static void ActualizarInventario(ArrayList<DetalleVenta> detalleVenta) throws ErrorTienda, SQLException{
        try{
            cn = new Conexion();
            PreparedStatement consulta;
            for(DetalleVenta d: detalleVenta){ //se recorre detalleventa y calcula la cantidad nueva del inventario
                
                int nuevoInventario = (d.getProducto().getInventario()-d.getCantidad());
                d.getProducto().setInventario(nuevoInventario);  //se elimino la query y se modifico por medio del objeto detalleventa
                
                
                consulta= cn.conexion.prepareStatement("UPDATE productos SET Inventario = ? WHERE CodBarra = ?");
                consulta.setInt(1, d.getProducto().getInventario());
                consulta.setString(2, d.getProducto().getCodBarra());
                consulta.executeUpdate();
            }
            
        }catch(SQLException ex){
            throw new ErrorTienda("Error SQL ControladorVenta.actualizarIventario", ex.getMessage());    
        }finally{
//            cn.conexion.close();
        }
        
    }

    
    
}