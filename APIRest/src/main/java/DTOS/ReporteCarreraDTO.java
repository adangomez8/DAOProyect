package DTOS;

import java.time.LocalDate;

public class ReporteCarreraDTO {
	private String nombreCarrera;
	private int año;
	private int inscriptos;
	private int egresados;
	public String getNombreCarrera() {
		return nombreCarrera;
	}
	public int getAño() {
		return año;
	}
	public int getInscriptos() {
		return inscriptos;
	}
	public int getEgresados() {
		return egresados;
	}
	public ReporteCarreraDTO(String nombreCarrera, int año, int egresados, int inscriptos) {
		super();
		this.nombreCarrera = nombreCarrera;
		this.año = LocalDate.now().getYear()-año;
		this.inscriptos = inscriptos;
		this.egresados = egresados;
	}
	
	
	
}
