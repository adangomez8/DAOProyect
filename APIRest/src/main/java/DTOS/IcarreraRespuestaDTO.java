package DTOS;

import TP3.APIRest.entities.Carrera;
import TP3.APIRest.entities.Estudiante;


//se usa para las respuestas para mostrar los datos que queres, pero a diferencia del otro dto aca si quiero toda la info de su carrera y estudiante
public class IcarreraRespuestaDTO {
	private boolean graduado;
	private int antiguedad;
	private Carrera carrera;
	private Estudiante estudiante;
	
	public IcarreraRespuestaDTO(boolean graduado,int antiguedad,Carrera carrera,Estudiante estudiante) {
		this.graduado=graduado;
		this.antiguedad=antiguedad;
		this.carrera=carrera;
		this.estudiante=estudiante;
		
	}

	public boolean isGraduado() {
		return graduado;
	}

	public int getAntiguedad() {
		return antiguedad;
	}

	public Carrera getCarrera() {
		return carrera;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}
}
