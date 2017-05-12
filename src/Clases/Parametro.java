/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Parametro {
    
public String direccion;
public String Nombre;
public String utilidad;
static Conexion cn;
static ResultSet rs;

    public static ArrayList<Parametro> Obtener() throws ErrorTienda{
        ArrayList<Object> parametro=new ArrayList<Object>();
        
        
        
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT direccion,Nombre,utilidad FROM parametro");
            while (rs.next()) {
                parametro.add(rs.getString(0));
                parametro.add(rs.getString(1));
                parametro.add(rs.getString(3));
            }
        } catch (Exception e) {
            throw new ErrorTienda("Class Parametro/Obtener",e.getMessage());
        }
        
        ArrayList<Parametro> parametros =(ArrayList) parametro;
        return parametros;
    }
    
    public static double  ObtenerUtilidad() throws ErrorTienda{

        double utilidad=0.0;
        cn=new Conexion();
        try {
            rs=cn.st.executeQuery("SELECT Valor FROM parametros WHERE IdParametro = 3");
            while (rs.next()) {
                utilidad = Double.parseDouble(rs.getString(1))/100;
            }
        } catch (Exception e) {
            throw new ErrorTienda("Class Parametro/ObtenerUtilidad",e.getMessage());
        }

        return utilidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(String utilidad) {
        this.utilidad = utilidad;
    }

    

    

}
 