package TP3.APIRest.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class InfoCarrera {
	
	@Id
	private int id;
	
	private boolean graduado;
	private int antiguedad;
	@ManyToOne
	@JoinColumn(name = "id_carrera")
	private Carrera carrera;
	
	public InfoCarrera() {
		
	}

	public InfoCarrera(boolean graduado, int antiguedad, Carrera carrera) {
		super();
		this.graduado = graduado;
		this.antiguedad = antiguedad;
		this.carrera = carrera;
	}

	public boolean isGraduado() {
		return graduado;
	}

	public void setGraduado(boolean graduado) {
		this.graduado = graduado;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "InfoCarrera [id=" + id + ", graduado=" + graduado + ", antiguedad=" + antiguedad + ", carrera="
				+ carrera + "]";
	}

}
