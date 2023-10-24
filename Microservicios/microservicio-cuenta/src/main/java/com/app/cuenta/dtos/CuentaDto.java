package com.app.cuenta.dtos;

import com.app.cuenta.models.entitys.Usuario;
import jakarta.persistence.ManyToMany;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;

public class CuentaDto {

    private int id;

    @Getter
    private String cuentaMercadoPago;

    @Getter
    private double saldo;

    @Getter
    private int usuariosAsociados;

    @Getter
    private LocalDate fechaDeAlta;


    public CuentaDto(int id, String cuentaMercadoPago, double saldo, int usuariosAsociados, LocalDate fechaDeAlta) {
        this.id = id;
        this.cuentaMercadoPago = cuentaMercadoPago;
        this.saldo = saldo;
        this.usuariosAsociados = usuariosAsociados;
        this.fechaDeAlta = fechaDeAlta;
    }

}
