package TP3.API.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import DTOS.EstudianteDTO;
import TP3.APIRest.entities.Estudiante;
import TP3.APIRest.repositories.EstudianteRepository;
import jakarta.transaction.Transactional;

@Service
public class EstudianteService {
	@Autowired
	private EstudianteRepository repository;
	

	
	public Estudiante SearchById(@PathVariable int id) {
		
		Optional<Estudiante> estudiante= repository.findById(id);
		
		if(estudiante.isPresent()) {
			return estudiante.get();
		}
		else {
			return null;
		}
	}
	@Transactional
	public Estudiante persist(@RequestBody Estudiante e) {
		if(repository.findById(e.getNroLibreta()).isEmpty()) {
			repository.save(e);
			return e;
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
	public List<Estudiante>findAll(){
		return repository.findAll();
	}
}
