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

import TP3.APIRest.entities.Carrera;
import TP3.APIRest.repositories.CarreraRepository;

@RestController
public class CarreraController {
	
	@Autowired
	private CarreraRepository repository;
	
	public CarreraController(CarreraRepository repository) {
		this.repository=repository;
	}
	
	@GetMapping("/api/carrera/{id}")
	public ResponseEntity<Carrera> SearchById(@PathVariable int id) {
		
		Optional<Carrera> carrera= repository.findById(id);
		
		if(carrera.isPresent()) {
			return ResponseEntity.ok(carrera.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/carrera")
	public ResponseEntity<Carrera> persist(@RequestBody Carrera c) {
		repository.save(c);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/api/carrera/{id}")
	public ResponseEntity<Carrera> delete(@PathVariable int id) {
		
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public List<Carrera> getAll(){
		return repository.findAll();
	}

}
