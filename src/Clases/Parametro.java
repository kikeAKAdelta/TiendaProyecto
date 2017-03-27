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
public class Parametro {
    
public String IdParametro;
public String Nombre;
public String Valor;

public static ArrayList<Parametro> Obtener(){
ArrayList<Parametro> parametro = new ArrayList<Parametro>();
return parametro;
}
public static Parametro ObtenerUtilidad(){
return new Parametro();
}

}
 