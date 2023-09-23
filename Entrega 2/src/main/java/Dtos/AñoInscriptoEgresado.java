/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ilari
 */
public class AñoInscriptoEgresado {
    private Map<Integer,Integer> añoInscripto=new HashMap();
    private  Map<Integer,Integer>añoEgresado=new HashMap();

    public AñoInscriptoEgresado() {
    }

 public void addInscripto(Integer año){
     if(añoInscripto.get(año)!=null)
        añoInscripto.put(año,añoInscripto.get(año)+1);
     else
         añoInscripto.put(año,1);
 } 
 
 public void addEgresado(Integer año){
     if(añoEgresado.get(año)!=null)
        añoEgresado.put(año,añoEgresado.get(año)+1);
     else
         añoEgresado.put(año, 1);
 }

    @Override
    public String toString() {
        return "AñoInscriptoEgresado{" + "Ingresados por año=" + añoInscripto + ", Egresados por año=" + añoEgresado + '}';
    }
    
}
