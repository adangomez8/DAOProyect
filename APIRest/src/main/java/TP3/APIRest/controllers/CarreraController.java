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

import DTOS.CarreraDTO;
import TP3.API.Services.CarreraService;
import TP3.APIRest.entities.Carrera;


@RestController
@RequestMapping("api/carrera")
public class CarreraController {
	
	@Autowired
	private CarreraService service;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> searchById(@PathVariable Integer id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.searchById(id));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. No se encuentra el objeto buscado" +
                    ".\"}");
		}
		
	}
	
	@PostMapping()
	public ResponseEntity<?> persist(@RequestBody Carrera c) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.save(c));
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("asegure de mandar bien el formato");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
	    CarreraDTO carreraDTO = service.deleteCarreraById(id);
	    if (carreraDTO != null) {
	        return ResponseEntity.status(HttpStatus.OK).body(carreraDTO);
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado");
	    }
	}

	
	@GetMapping()
	public ResponseEntity<?> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

}
