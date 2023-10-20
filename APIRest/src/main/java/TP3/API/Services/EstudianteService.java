package TP3.API.Services;

import java.util.ArrayList;
import java.util.Comparator;
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
	public List<EstudianteDTO>findAll(String carrera,String ciudad,String genre,String orderby){
		
		if(((carrera!=null)&&(!carrera.isEmpty()))||((ciudad!=null)&&(!ciudad.isEmpty()))||((genre!=null)&&(!genre.isEmpty()))||((orderby!=null)&&(!orderby.isEmpty()))) {
			if(carrera==null || carrera.isEmpty())
				carrera=null;
			if(ciudad==null || ciudad.isEmpty())
				ciudad=null;
			if(genre==null || genre.isEmpty())
				genre=null;
			if(orderby==null || orderby.isEmpty())
				orderby=null;
			List<Estudiante> estudiantes = repository.findAll(carrera, ciudad, genre);
	        if ((orderby != null)&&(!orderby.isEmpty())) {
	        	
	            estudiantes = ordenarEstudiantes(estudiantes, orderby);
	        }
	        return this.transformDTO(estudiantes);
			}
			
		return this.transformDTO(repository.findAll());
		}
	
	private List<Estudiante> ordenarEstudiantes(List<Estudiante> estudiantes, String orderby) {
	    switch (orderby) {
	        case "ciudad":
	            estudiantes.sort(Comparator.comparing(Estudiante::getCiudad));
	            break;

	        case "nroLibreta":
	            estudiantes.sort(Comparator.comparing(Estudiante::getNroLibreta));
	            break;

	        case "nombre":
	            estudiantes.sort(Comparator.comparing(Estudiante::getNombre));
	            break;

	        case "genero":
	            estudiantes.sort(Comparator.comparing(Estudiante::getGenero));
	            break;
	    }
	    return estudiantes;
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
