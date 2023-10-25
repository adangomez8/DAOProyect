package com.app.monopatin.models.clases;

public class Monopatin {

    private int id;

    private String estado;

    private int kilometros;

    private boolean enMantenimiento;

    public Monopatin(int id, String estado, int kilometros, boolean enMantenimiento) {
        this.id = id;
        this.estado = estado;
        this.kilometros = kilometros;
        this.enMantenimiento = enMantenimiento;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setKilometros(int kilometros) {
        this.kilometros = kilometros;
    }

    public void setEnMantenimiento(boolean enMantenimiento) {
        this.enMantenimiento = enMantenimiento;
    }
}
