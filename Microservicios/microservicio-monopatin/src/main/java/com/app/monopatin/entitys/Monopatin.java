package com.app.monopatin.entitys;

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

    private String latitud;

    private String longitud;

    private int tiempoEnUso;

    private int tiempoEnPausa;

    @ManyToOne
    private Parada parada;

    public Monopatin(){

    }

    public Monopatin(int id, String estado, String latitud, String longitud, Parada parada) {
        this.id = id;
        this.estado = estado;
        this.kilometros = 0;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoEnUso = 0;
        this.tiempoEnPausa = 0;
        this.parada = parada;
    }


}
