package com.app.monopatin.models.entitys;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;

@Entity
@Data
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String coordenadas;

    @OneToMany(mappedBy="parada")
    private ArrayList<Monopatin> monopatines;

    public Parada() {
        this.monopatines = new ArrayList<>();
    }

    public Parada(int id, String coordenadas) {
        this.id = id;
        this.coordenadas = coordenadas;
        this.monopatines = new ArrayList<>();
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}
