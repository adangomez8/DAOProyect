package DTOS;

public class IcarreraDTO {
	private boolean graduado;
	private int antiguedad;
	private int carrera;
	private int estudiante;
	
	public IcarreraDTO(boolean graduado,int antiguedad,int carrera,int estudiante) {
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

	public int getCarrera() {
		return carrera;
	}

	public int getEstudiante() {
		return estudiante;
	}
	
	
}
