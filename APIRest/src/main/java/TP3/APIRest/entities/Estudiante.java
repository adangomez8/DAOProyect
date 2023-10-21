package TP3.APIRest.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Estudiante {
	
	@Id
	private Integer nroLibreta;
	
	private  int dni;
	private String nombre;
	private String apellido;
	private int edad;
	private String genero;
	private String ciudad;
	@OneToMany(mappedBy="estudiante")
	@JsonIgnore
	private List<InfoCarrera>carreras;
	
	public Estudiante() {
		
	}

	public Estudiante(int dni, String nombre, String apellido, int edad, String genero, String ciudad
			) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.ciudad = ciudad;
		this.carreras = new ArrayList<>();
	}
	public void addCarrera(InfoCarrera c){
		this.carreras.add(c);
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public List<InfoCarrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<InfoCarrera> carreras) {
		this.carreras = carreras;
	}

	public int getNroLibreta() {
		return nroLibreta;
	}

	
	
	@Override
	public String toString() {
		return "Estudiante [nroLibreta=" + nroLibreta + ", dni=" + dni + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", edad=" + edad + ", genero=" + genero + ", ciudad=" + ciudad + ", carreras=" + carreras + "]";
	}

}
