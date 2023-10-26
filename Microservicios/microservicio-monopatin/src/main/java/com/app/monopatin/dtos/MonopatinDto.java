package com.app.monopatin.dtos;

import lombok.Getter;

public class MonopatinDto {

    private Integer id;

    private String estado;

    private int kilometros;

    private String latitud;

    private String longitud;

    private int tiempoEnUso;

    private int tiempoEnPausa;

    public MonopatinDto(Integer id, String estado, int kilometros, String latitud, String longitud, int tiempoEnUso, int tiempoEnPausa) {
        this.id = id;
        this.estado = estado;
        this.kilometros = kilometros;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoEnUso = tiempoEnUso;
        this.tiempoEnPausa = tiempoEnPausa;
    }

    public Integer getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public int getKilometros() {
        return kilometros;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public int getTiempoEnUso() {
        return tiempoEnUso;
    }

    public int getTiempoEnPausa() {
        return tiempoEnPausa;
    }
}
