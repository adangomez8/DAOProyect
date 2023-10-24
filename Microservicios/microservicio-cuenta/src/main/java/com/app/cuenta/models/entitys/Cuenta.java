package com.app.cuenta.models.entitys;


import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Cuenta {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Getter
    private String cuentaMercadoPago;

    @Getter
    private double saldo;

    @ManyToMany
    private ArrayList<Usuario> usuariosAsociados;

    @Getter
    private LocalDate fechaDeAlta;

    public Cuenta (){
        this.usuariosAsociados = new ArrayList<>();
    }

    public Cuenta(int id, String cuentaMercadoPago, double saldo, LocalDate fechaDeAlta) {
        this.id = id;
        this.cuentaMercadoPago = cuentaMercadoPago;
        this.saldo = saldo;
        this.usuariosAsociados = new ArrayList<>();
        this.fechaDeAlta = fechaDeAlta;
    }

    public void setCuentaMercadoPago(String cuentaMercadoPago) {
        this.cuentaMercadoPago = cuentaMercadoPago;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void setUsuariosAsociados(ArrayList<Usuario> usuariosAsociados) {
        this.usuariosAsociados = usuariosAsociados;
    }

    public void setFechaDeAlta(LocalDate fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public ArrayList<Usuario> getUsuariosAsociados() {
        return new ArrayList<Usuario>(usuariosAsociados);
    }
}
