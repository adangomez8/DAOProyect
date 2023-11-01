package com.Microservicios.Mantenimiento.Dto;

import lombok.Data;

@Data
public class ReporteWithPause {
    private Integer id;
    private int tiempoEnPausa;

    public ReporteWithPause(Integer id, int tiempoEnPausa) {
        this.id = id;
        this.tiempoEnPausa = tiempoEnPausa;
    }
}
