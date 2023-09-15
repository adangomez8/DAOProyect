package Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Carrera {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String nombre;
	
	@Column
	private double duracion;
	
	@OneToMany
	private List<Estudiante> estudiantes;

	public String getNombre() {
		return nombre;
	}

	public void addEstudiante(Estudiante e) {
		estudiantes.add(e);
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getDuracion() {
		return duracion;
	}

	public void setDuracion(double duracion) {
		this.duracion = duracion;
	}

	public Carrera(String nombre, double duracion) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
	}

	public Carrera() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
