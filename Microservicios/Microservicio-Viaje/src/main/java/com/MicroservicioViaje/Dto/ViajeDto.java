package com.MicroservicioViaje.Dto;

import java.util.Date;

public class ViajeDto {
    private int id_viaje;
    private int id_cuenta,id_monopatin;
    private Date fecha_inicio,fecha_fin;
    private double km_recorridos,precio,tarifa_extra;

    public ViajeDto(int id_viaje, int id_cuenta, int id_monopatin, Date fecha_inicio, Date fecha_fin, double km_recorridos, double precio, double tarifa_extra) {
        this.id_viaje = id_viaje;
        this.id_cuenta = id_cuenta;
        this.id_monopatin = id_monopatin;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.km_recorridos = km_recorridos;
        this.precio = precio;
        this.tarifa_extra = tarifa_extra;
    }

    @Override
    public String toString() {
        return "ViajeDto{" +
                "id_viaje=" + id_viaje +
                ", id_cuenta=" + id_cuenta +
                ", id_monopatin=" + id_monopatin +
                ", fecha_inicio=" + fecha_inicio +
                ", fecha_fin=" + fecha_fin +
                ", km_recorridos=" + km_recorridos +
                ", precio=" + precio +
                ", tarifa_extra=" + tarifa_extra +
                '}';
    }
}
