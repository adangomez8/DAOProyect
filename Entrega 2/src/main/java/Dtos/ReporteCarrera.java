/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author ilari
 */
public class ReporteCarrera implements Serializable{
    String nombre;
    AñoInscriptoEgresado datos;

    public String getNombre() {
        return nombre;
    }

    public AñoInscriptoEgresado getDatos() {
        return datos;
    }

    public ReporteCarrera(String nombre) {
        this.nombre = nombre;
        datos=new AñoInscriptoEgresado();
    }

    @Override
    public String toString() {
        return "ReporteCarrera{" + "nombre=" + nombre + ", datos=" + datos + '}';
    }
    
}
