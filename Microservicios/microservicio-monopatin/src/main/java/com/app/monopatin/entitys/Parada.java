package com.app.monopatin.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Parada {

    @Id
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
