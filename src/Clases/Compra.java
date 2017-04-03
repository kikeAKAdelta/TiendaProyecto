/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static Clases.ControladorProducto.cn;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pedro
 */
public class Compra {
    
public int IdCompra;
public Date Fecha;
public Proveedor PROVEEDOR;
public double Total;
public ArrayList<DetalleCompra> ARTICULOS;
        
        public void CalcularTotal() {}
        
        public void AgregarItem(DetalleCompra dc){
           try {
            cn.st.executeUpdate("INSERT INTO detallecompra(CodBarra,IdCompra,Cantidad,CostoUnitario) VALUES('"+dc.PRODUCTO.CodBarra+"','"+IdCompra+"','"+dc.Cantidad+"','"+dc.CostoUnitario+"')");
        } catch (SQLException ex) {
            Logger.getLogger(ControladorProducto.class.getName()).log(Level.SEVERE, null, ex);
        }
        }        
}
