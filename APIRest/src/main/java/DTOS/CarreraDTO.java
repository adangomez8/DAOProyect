package DTOS;

public class CarreraDTO {
	private Integer id;
    private String nombre;
    private int duracion;
    
	public CarreraDTO(Integer id, String nombre, int duracion) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.duracion = duracion;
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
