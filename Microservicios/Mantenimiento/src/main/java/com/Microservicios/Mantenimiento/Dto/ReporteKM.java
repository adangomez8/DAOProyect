package com.Microservicios.Mantenimiento.Dto;

import lombok.Data;

@Data
public class ReporteKM {
    private Integer id;
    private double km_recorrido;

    public ReporteKM(Integer id, double km_recorrido) {
        this.id = id;
        this.km_recorrido = km_recorrido;
    }

}
