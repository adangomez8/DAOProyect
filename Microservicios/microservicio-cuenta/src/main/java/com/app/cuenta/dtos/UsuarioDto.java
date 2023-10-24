package com.app.cuenta.dtos;

public class UsuarioDto {

    private int id;

    private String nombreUsuario;

    private String nombre;

    private String apellido;

    private int nroCelular;

    public UsuarioDto(String nombreUsuario, String nombre, String apellido, int nroCelular) {
        this.nombreUsuario = nombreUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroCelular = nroCelular;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNroCelular() {
        return nroCelular;
    }

    public void setNroCelular(int nroCelular) {
        this.nroCelular = nroCelular;
    }
}
