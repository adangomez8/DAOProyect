package TP3.APIRest.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Carrera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private int duracion;
	@OneToMany(mappedBy = "carreras")
	private List<Estudiante>estudiantes;
	
	public Carrera() {
		
	}

	public Carrera(String nombre, int duracion, List<Estudiante> estudiantes) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;
		this.estudiantes = estudiantes;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + ", estudiantes=" + estudiantes
				+ "]";
	}

}
