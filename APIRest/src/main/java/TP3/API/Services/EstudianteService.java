package TP3.API.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import DTOS.EstudianteDTO;
import TP3.APIRest.entities.Carrera;
import TP3.APIRest.entities.Estudiante;
import TP3.APIRest.repositories.EstudianteRepository;
import jakarta.transaction.Transactional;

@Service
public class EstudianteService {
	@Autowired
	private EstudianteRepository repository;
	

	@Transactional
	public EstudianteDTO SearchById(@PathVariable int id) {
		
		Optional<Estudiante> estudiante= repository.findById(id);
		
		if(estudiante.isPresent()) {
			Estudiante e=estudiante.get();
			return (new EstudianteDTO(e.getNroLibreta(),e.getApellido(),e.getCiudad(),e.getDni(),e.getEdad(),e.getGenero(),e.getNombre()));
		}
		else {
			return null;
		}
	}
	@Transactional
	public Estudiante SearchByIdESTUDIANTE(@PathVariable int id) {
		
		Optional<Estudiante> estudiante= repository.findById(id);
		
		if(estudiante.isPresent()) {
			return estudiante.get();
			
		}
		else {
			return null;
		}
	}
	@Transactional
	public EstudianteDTO persist(@RequestBody Estudiante e) {
		if(repository.findById(e.getNroLibreta()).isEmpty()) {
			repository.save(e);
			return (this.transformOne(e));
		}else {
			return null;
		}
	}
	@Transactional
	public EstudianteDTO delete(@PathVariable int id) {
	    Optional<Estudiante> optionalEstudiante = repository.findById(id);
	    if (optionalEstudiante.isPresent()) {
	        Estudiante estudiante = optionalEstudiante.get();
	        repository.deleteById(id);
	        EstudianteDTO estudianteDTO = new EstudianteDTO(estudiante.getNroLibreta(), estudiante.getApellido(), estudiante.getCiudad(),
	                estudiante.getDni(), estudiante.getEdad(), estudiante.getGenero(), estudiante.getNombre());
	        
	        return estudianteDTO;
	    } else {
	    	return null;
	    }
	}
	@Transactional
	public List<EstudianteDTO>findByGenre(String genre){
		return this.transformDTO(repository.getEstudiantePorGenero(genre));
	}
	@Transactional
	public List<EstudianteDTO>findByCarreerAndCity(String carrera,String ciudad){
		return this.transformDTO(repository.getEstudiantesPorCarreraYCiudad(carrera,ciudad));
	}
	@Transactional
	public List<EstudianteDTO>findAll(){
		return this.transformDTO(repository.findAll());
	}
	@Transactional
	public List<EstudianteDTO>findAllOrder(String order){
		List<Estudiante>estudiantes= repository.findAll();
		estudiantes.sort((estudiante1, estudiante2) -> {
	        switch (order) {
	            case "nombre":
	                return estudiante1.getNombre().compareTo(estudiante2.getNombre());
	            case "ciudad":
	                return estudiante1.getCiudad().compareTo(estudiante2.getCiudad());
	            case "apellido":
	            	return estudiante1.getApellido().compareTo(estudiante2.getApellido());
	            case "genero":
	            	return estudiante1.getGenero().compareTo(estudiante2.getGenero());
	            case "dni":
	            	int result=estudiante1.getDni()-estudiante2.getDni();
	            	if(result<0)
	            		return -1;
	            	else if(result>0)
	            		return 1;
    				return 0;
	            case "edad":
	            	int result2=estudiante1.getEdad()-estudiante2.getEdad();
	            	if(result2<0)
	            		return -1;
	            	else if(result2>0)
	            		return 1;
    				return 0;
	            default:
	                return 0; 
	        }
	    });
		return this.transformDTO(estudiantes);

	}
	
	private List<EstudianteDTO> transformDTO(List<Estudiante>estudiantes){
		List<EstudianteDTO>estudiantesDto=new ArrayList();
		for(Estudiante e:estudiantes) {
			estudiantesDto.add(this.transformOne(e));
		}
		return estudiantesDto;
	}
	private EstudianteDTO transformOne(Estudiante e) {
		return (new EstudianteDTO(e.getNroLibreta(),e.getApellido(),e.getCiudad(),e.getDni(),e.getEdad(),e.getGenero(),e.getNombre()));
	}
}
