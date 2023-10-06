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

import DTOS.IcarreraDTO;
import TP3.API.Services.InfoCarreraService;
import TP3.APIRest.entities.Carrera;
import TP3.APIRest.entities.Estudiante;
import TP3.APIRest.entities.InfoCarrera;
import TP3.APIRest.repositories.CarreraRepository;
import TP3.APIRest.repositories.EstudianteRepository;
import TP3.APIRest.repositories.InfoCarreraRepository;

@RestController
@RequestMapping("api")
public class InfoCarreraController {
	
	@Autowired
	private InfoCarreraService service;
	
	@GetMapping("/icarrera/{id}")
	public ResponseEntity<?> SearchById(@PathVariable Integer id) {
		InfoCarrera i=service.SearchById(id);
		if(i!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(i);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
	}
	
	@PostMapping("/icarrera")
	public ResponseEntity<?> persist(@RequestBody IcarreraDTO c) {
		InfoCarrera i=service.persist(c);
		if(i!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(i);
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error revisar formato");
	}
	
	@DeleteMapping("/icarrera/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		IcarreraDTO i=service.delete(id);
		if(i!=null) {
			Map<String, Object> response = new HashMap<>();
	        response.put("message", "borrado");
	        response.put("info carrera ", i);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
	}
	
	@GetMapping("/icarrera")
	public ResponseEntity<?> getAlls(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAlls());
	}

}
