package com.app.monopatin.dtos;

import lombok.Getter;

public class MonopatinDto {

    @Getter
    private int id;

    @Getter
    private String estado;

    @Getter
    private int kilometros;

    @Getter
    private boolean enMantenimiento;

    public MonopatinDto(int id, String estado, int kilometros, boolean enMantenimiento) {
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
