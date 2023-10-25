package com.app.monopatin.models.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Monopatin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String estado;

    private int kilometros;

    @ManyToOne()
    private Parada ubicacionInicial;

    private boolean enMantenimiento;

    public Monopatin(){

    }

    public Monopatin(String estado, int kilometros, boolean enMantenimiento) {
        this.estado = estado;
        this.kilometros = kilometros;
        this.enMantenimiento = enMantenimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getKilometros() {
        return kilometros;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public boolean isEnMantenimiento() {
        return enMantenimiento;
    }

    public void setEnMantenimiento(boolean enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
    }
}
