package TP3.APIRest.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("api/estudiante")
public class EstudianteController {
	
	@Autowired
	private EstudianteRepository repository;
	
	public EstudianteController(EstudianteRepository repository) {
		this.repository=repository;
	}
	
	@GetMapping("/id={id}")
	public ResponseEntity<Estudiante> SearchById(@PathVariable Integer id) {
		
		Optional<Estudiante> estudiante= repository.findById(id);
		
		if(estudiante.isPresent()) {
			return ResponseEntity.ok(estudiante.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping()
	public ResponseEntity<?> persist(@RequestBody Estudiante e) {
		if(repository.findById(e.getNroLibreta()).isEmpty()) {
			repository.save(e);
			return ResponseEntity.status(HttpStatus.CREATED).body(e);
		}else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya hay un estudiante con esa libreta");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		if(repository.existsById(id)) {
			repository.deleteById(id);
			//cambiar
			return ResponseEntity.status(HttpStatus.OK).body("borrado");
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
		}
	}
	
	@GetMapping()
	public List<Estudiante>findAll(){
		return repository.findAll();
	}

}
