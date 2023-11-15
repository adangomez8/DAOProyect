package com.app.monopatin.dtos;

<<<<<<< HEAD
import com.app.monopatin.entitys.Parada;

=======
import lombok.Data;
>>>>>>> 34311474fef081243ba347389b80b53d1d074ae3
import lombok.Getter;

@Data
public class MonopatinDto {

    private Integer id;

    private String estado;

    private int kilometros;

    private String latitud;

    private String longitud;

    private int tiempoEnUso;

    private int tiempoEnPausa;
    
    private Parada parada;

    private int idParada;

    public MonopatinDto(Integer id, String estado, int kilometros, String latitud, String longitud, int tiempoEnUso, int tiempoEnPausa,Parada parada) {
        this.id = id;
        this.estado = estado;
        this.kilometros = kilometros;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoEnUso = tiempoEnUso;
        this.tiempoEnPausa = tiempoEnPausa;
        this.parada=parada;
    }

    public Integer getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public int getKilometros() {
        return kilometros;
    }

    public String getLatitud() {
        return latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public int getTiempoEnUso() {
        return tiempoEnUso;
    }

    public int getTiempoEnPausa() {
        return tiempoEnPausa;
    }

	public Parada get_parada() {
		return parada;
	}
    
}
