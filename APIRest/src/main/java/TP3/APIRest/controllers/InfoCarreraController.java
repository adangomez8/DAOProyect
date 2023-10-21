package TP3.APIRest.controllers;

import java.util.HashMap;
import java.util.Map;

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
import DTOS.IcarreraRespuestaDTO;
import TP3.API.Services.InfoCarreraService;


@RestController
@RequestMapping("api/icarrera")
public class InfoCarreraController {
	
	@Autowired
	private InfoCarreraService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> SearchById(@PathVariable Integer id) {
		IcarreraRespuestaDTO i=service.SearchById(id);
		if(i!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(i);
		}
		else
			return ResponseEntity.status(HttpStatus.OK).body("no se encontraron resultados");
	}
	
	@PostMapping("")
	public ResponseEntity<?> persist(@RequestBody IcarreraDTO c) {
		IcarreraRespuestaDTO i=service.persist(c);
		if(i!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(i);
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error revisar formato");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		
		IcarreraRespuestaDTO i=service.delete(id);
		if(i!=null) {
			Map<String, Object> response = new HashMap<>();
	        response.put("message", "borrado");
	        response.put("info carrera ", i);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
	}
	
	@GetMapping("")
	public ResponseEntity<?> getAlls(){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAlls());
	}

}
