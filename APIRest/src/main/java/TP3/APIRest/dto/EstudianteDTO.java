package TP3.APIRest.dto;


public class EstudianteDTO {

    private Long nroDoc;
    private String nombre;
    private String apellido;
    private int nroLibreta;

    // Constructores, getters y setters

    public EstudianteDTO() {
    }

    public EstudianteDTO(Long nroDoc, String nombre, String apellido, int nroLibreta) {
        this.nroDoc = nroDoc;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nroLibreta = nroLibreta;
    }

    public Long getNroDoc() {
        return nroDoc;
    }

    public void setNroDoc(Long nroDoc) {
        this.nroDoc = nroDoc;
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

    public int getNroLibreta() {
        return nroLibreta;
    }

    public void setNroLibreta(int nroLibreta) {
        this.nroLibreta = nroLibreta;
    }


}
