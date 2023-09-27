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

import TP3.APIRest.entities.InfoCarrera;
import TP3.APIRest.repositories.InfoCarreraRepository;

@RestController
public class InfoCarreraController {
	
	@Autowired
	private InfoCarreraRepository repository;
	
	public InfoCarreraController(InfoCarreraRepository repository) {
		this.repository=repository;
	}
	
	@GetMapping("/api/icarrera/{id}")
	public ResponseEntity<InfoCarrera> SearchById(@PathVariable int id) {
		
		Optional<InfoCarrera> icarrera= repository.findById(id);
		
		if(icarrera.isPresent()) {
			return ResponseEntity.ok(icarrera.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/api/icarrera")
	public ResponseEntity<InfoCarrera> persist(@RequestBody InfoCarrera c) {
		repository.save(c);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/api/icarrera/{id}")
	public ResponseEntity<InfoCarrera> delete(@PathVariable int id) {
		
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public List<InfoCarrera> getAll(){
		return repository.findAll();
	}

}
