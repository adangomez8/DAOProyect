package Entities;

import java.time.LocalDate;
import java.util.Date;
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
        @JoinColumn(name="id_carrera")
	private Carrera carrera;
        
	@Column
	private boolean graduado=false;
	
        @Column
        private LocalDate fechaGraduacion=null;
        
	@Column
	private int antiguedad;

	public InfoCarrera(  Carrera c,boolean graduado, int antiguedad,LocalDate graduacion) {
		super();
		fechaGraduacion=graduacion;
		this.carrera=c;
		this.graduado = graduado;
		this.antiguedad = antiguedad;
	}
	
	public InfoCarrera() {
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

    public LocalDate getFechaGraduacion() {
        return fechaGraduacion;
    }

        
        
    public int getId() {
        return id;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    @Override
    public String toString() {
        return "InfoCarrera{" + "id=" + id + ", carrera="  +carrera.getNombre()+ ", graduado=" + graduado + ", antiguedad=" + antiguedad + '}';
    }

	
	
}
