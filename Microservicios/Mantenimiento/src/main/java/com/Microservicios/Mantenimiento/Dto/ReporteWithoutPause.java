package com.Microservicios.Mantenimiento.Dto;

import lombok.Data;

@Data
public class ReporteWithoutPause {
    private Integer id;
    private int tiempo_sin_pausa;

    public ReporteWithoutPause(Integer id, int tiempo_sin_pausa) {
        this.id = id;
        this.tiempo_sin_pausa = tiempo_sin_pausa;
    }
}
