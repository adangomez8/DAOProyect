package com.app.monopatin.dtos;

import com.app.monopatin.models.entitys.Monopatin;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;

public class ParadaDto {

    private int id;

    private String coordenadas;
    private int cantMonopatines;

    public ParadaDto(int id, String coordenadas, int cantMonopatines) {
        this.id = id;
        this.coordenadas = coordenadas;
        this.cantMonopatines = cantMonopatines;
    }

    public int getCantMonopatines() {
        return cantMonopatines;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }
}
