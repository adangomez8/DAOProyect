package DTOS;

public class CarreraDTO {
	private Integer id;
    private String nombre;
    private int duracion;
    private int estudiantes;
    
	public CarreraDTO(Integer id, String nombre, int duracion,int estudiantes) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
		this.estudiantes=estudiantes;
	}

	public int getEstudiantes() {
		return estudiantes;
	}

	public Integer getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public int getDuracion() {
		return duracion;
	}
    
    
}
