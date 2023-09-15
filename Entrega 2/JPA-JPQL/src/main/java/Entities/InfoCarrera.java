package Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class InfoCarrera {
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="ID_Carrera")
	private Carrera carrera;
	
	@Column
	private boolean graduado=false;
	
	@Column
	private int antiguedad;

	public InfoCarrera( Carrera carrera, boolean graduado, int antiguedad) {
		super();
		
		this.carrera = carrera;
		this.graduado = graduado;
		this.antiguedad = antiguedad;
	}
	
	public InfoCarrera() {
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
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

	
	
}
