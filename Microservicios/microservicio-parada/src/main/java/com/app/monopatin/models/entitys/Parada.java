package com.app.monopatin.models.entitys;


import com.app.monopatin.models.clases.Monopatin;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@Entity
@Data
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String coordenadas;

    @OneToMany(mappedBy="parada")
    private HttpEntity<Monopatin> monopatines;

    public Parada() {
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
