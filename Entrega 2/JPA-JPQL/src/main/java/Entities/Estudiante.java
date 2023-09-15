package Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Estudiante {

	
	@Id
	private int nroDoc;
	
	@Column
	private String nombre;
	
	@Column
	private String apellido;
	
	@Column
	private int nroLibreta;
	
	@OneToMany
	private List<InfoCarrera> carreras;
	
	@Column
	private String localidad;

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

	public int getNroLibreta() {
		return nroLibreta;
	}

	public void setNroLibreta(int nroLibreta) {
		this.nroLibreta = nroLibreta;
	}

	public List<InfoCarrera> getCarreras() {
		return carreras;
	}

	public void addCarrera(Carrera carrera,boolean graduado, int antiguedad) {
		InfoCarrera c=new InfoCarrera(carrera,graduado,antiguedad);
		carreras.add(c);
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Estudiante(int nroDoc, String nombre, String apellido, int nroLibreta, String localidad) {
		super();
		this.nroDoc = nroDoc;
		this.nombre = nombre;
		this.apellido = apellido;
		this.nroLibreta = nroLibreta;
		this.localidad = localidad;
	}

	public Estudiante() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
