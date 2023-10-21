package TP3.APIRest.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Carrera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nombre;
	private int duracion;
	@OneToMany(mappedBy="carrera")
	@JsonIgnore
	private List<InfoCarrera>infoCarreras=new ArrayList<InfoCarrera>();
	
	public Carrera() {
		
	}

	public Carrera(String nombre, int duracion) {
		super();
		this.nombre = nombre;
		this.duracion = duracion;

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
		List<Estudiante> estudiantes=new ArrayList<>();
		for(InfoCarrera i:this.infoCarreras) {
			estudiantes.add(i.getEstudiante());
		}
		return estudiantes;
	}


	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Carrera [id=" + id + ", nombre=" + nombre + ", duracion=" + duracion 
				+ "]";
	}
	@Override
	public boolean equals(Object o) {
		Carrera c=(Carrera)o;
		return ((this.nombre.equals(c.nombre))&&(this.duracion==c.duracion));
	}
}
