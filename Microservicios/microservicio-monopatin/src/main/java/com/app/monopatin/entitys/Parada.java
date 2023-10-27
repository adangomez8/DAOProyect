package com.app.monopatin.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String latitud;

    private String longitud;

    @OneToMany(mappedBy = "parada")
    private List<Monopatin> monopatines;

    public Parada() {
        this.monopatines = new ArrayList<>();
    }

    public Parada(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.monopatines = new ArrayList<>();
    }


}
