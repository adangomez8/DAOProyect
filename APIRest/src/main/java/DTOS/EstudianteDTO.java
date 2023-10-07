package DTOS;

public class EstudianteDTO {
	private int libreta;
	private String apellido;
	private String ciudad;
	private int dni;
	private int edad;
	private String genero;
	private String nombre;
	
	public int getLibreta() {
		return libreta;
	}
	public String getApellido() {
		return apellido;
	}
	public String getCiudad() {
		return ciudad;
	}
	public int getDni() {
		return dni;
	}
	public int getEdad() {
		return edad;
	}
	public String getGenero() {
		return genero;
	}
	public String getNombre() {
		return nombre;
	}
	public EstudianteDTO(int libreta, String apellido, 
			String ciudad, int dni, int edad, String genero, 
				String nombre) {
		super();
		this.libreta = libreta;
		this.apellido = apellido;
		this.ciudad = ciudad;
		this.dni = dni;
		this.edad = edad;
		this.genero = genero;
		this.nombre = nombre;
	}

	
	
}
