/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;

/**
 *
 * @author oscar
 */
public class Parametros {
    
public String IdParametro;
public String Nombre;
public String Valor;

public static ArrayList<Parametros[]> Obtener(){
ArrayList<Parametros[]> parametro = new ArrayList<Parametros[]>();
return parametro;
}
public static Parametros ObtenerUtilidad(){
return new Parametros();
}

}
 