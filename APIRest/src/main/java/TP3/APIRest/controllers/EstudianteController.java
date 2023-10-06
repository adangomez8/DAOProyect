package TP3.APIRest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import DTOS.EstudianteDTO;
import TP3.API.Services.EstudianteService;
import TP3.APIRest.entities.Estudiante;
import TP3.APIRest.repositories.EstudianteRepository;

@RestController
@RequestMapping("api/estudiante")
public class EstudianteController {
	
	@Autowired
	private EstudianteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> SearchById(@PathVariable int id) {
		Estudiante e=service.SearchById(id);
		if(e!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(e);
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
	}
	
	@PostMapping()
	public ResponseEntity<?> persist(@RequestBody Estudiante e) {
		Estudiante estudiante=service.persist(e);
		if(estudiante!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(estudiante);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error revisar formato");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		EstudianteDTO e=service.delete(id);
		if(e!=null) {
			Map<String, Object> response = new HashMap<>();
	        response.put("message", "borrado");
	        response.put("estudiante", e);
	        return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		
	    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
	}

	
	@GetMapping()
	public ResponseEntity<?>findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

}
