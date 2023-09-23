package Entities;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	
	@OneToMany()
        private List<Estudiante> estudiantes;

    public int getId() {
        return id;
    }

        
        
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
                estudiantes=new ArrayList();
	}

	public Carrera() {
		super();
		// TODO Auto-generated constructor stub
	}
        
        public List<Estudiante> getEstudiantes(){
            return new ArrayList(estudiantes);
        }
        
        public boolean equals(Object o){
            Carrera c=(Carrera)o;
            return this.getId()==c.getId();
        }
        
    @Override
    public String toString() {
        return "Carrera{" + "id=" + id + ", nombre=" + nombre + ", duracion=" + duracion + '}';
    }
	
	
	
	
}
