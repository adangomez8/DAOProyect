package TP3.APIRest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import TP3.APIRest.entities.Estudiante;
import TP3.APIRest.repositories.EstudianteRepository;

@RestController
@RequestMapping("api")
public class EstudianteController {
	
	@Autowired
	private EstudianteRepository repository;
	
	public EstudianteController(EstudianteRepository repository) {
		this.repository=repository;
	}
	
	@GetMapping("/estudiante/{id}")
	public ResponseEntity<Estudiante> SearchById(@PathVariable Integer id) {
		
		Optional<Estudiante> estudiante= repository.findById(id);
		
		if(estudiante.isPresent()) {
			return ResponseEntity.ok(estudiante.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/estudiante")
	public ResponseEntity<Estudiante> persist(@RequestBody Estudiante e) {
		repository.save(e);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/estudiante/{id}")
	public ResponseEntity<Estudiante> delete(@PathVariable Integer id) {
		
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/estudiantes/")
	public List<Estudiante>findAll(){
		return repository.findAll();
	}

}
