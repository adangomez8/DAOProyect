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
import org.springframework.web.bind.annotation.RestController;

import TP3.APIRest.entities.Estudiante;
import TP3.APIRest.repositories.EstudianteRepository;

@RestController
public class EstudianteController {
	
	@Autowired
	private EstudianteRepository repository;
	
	public EstudianteController(EstudianteRepository repository) {
		this.repository=repository;
	}
	
	@GetMapping("/api/estudiante/{id}")
	public ResponseEntity<Estudiante> SearchById(@PathVariable int id) {
		
		Optional<Estudiante> estudiante= repository.findById(id);
		
		if(estudiante.isPresent()) {
			return ResponseEntity.ok(estudiante.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/estudiante")
	public ResponseEntity<Estudiante> persist(@RequestBody Estudiante e) {
		repository.save(e);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/api/estudiante/{id}")
	public ResponseEntity<Estudiante> delete(@PathVariable int id) {
		
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public List<Estudiante> getAll(){
		return repository.findAll();
	}

}
