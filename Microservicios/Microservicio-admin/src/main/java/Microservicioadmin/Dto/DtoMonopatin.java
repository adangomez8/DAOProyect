package Microservicioadmin.Dto;

public class DtoMonopatin {
	
	private Integer id;

    private String estado;

    private int kilometros;

    private String latitud;

    private String longitud;

    private int tiempoEnUso;

    private int tiempoEnPausa;

    public DtoMonopatin(Integer id, String estado, int kilometros, String latitud, String longitud, int tiempoEnUso, int tiempoEnPausa) {
        this.id = id;
        this.estado = estado;
        this.kilometros = kilometros;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoEnUso = tiempoEnUso;
        this.tiempoEnPausa = tiempoEnPausa;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}

	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}

	public void setTiempoEnUso(int tiempoEnUso) {
		this.tiempoEnUso = tiempoEnUso;
	}

	public void setTiempoEnPausa(int tiempoEnPausa) {
		this.tiempoEnPausa = tiempoEnPausa;
	}
    
    

}
