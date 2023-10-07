package TP3.APIRest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DTOS.CarreraDTO;
import DTOS.ReporteCarreraDTO;
import TP3.API.Services.CarreraService;
import TP3.APIRest.entities.Carrera;


@RestController
@RequestMapping("api/carrera")
public class CarreraController {
	
	@Autowired
	private CarreraService service;
	
	
	@GetMapping("/{id}")
	public ResponseEntity<?> searchById(@PathVariable Integer id) {
		CarreraDTO c=service.searchById(id);
		if(c!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(c);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
	}
	@GetMapping("/conestudiantesordenada")
	public ResponseEntity<?> searchByWithStudentsOrder() {
		List<?> c=service.findAllWithStudents();
		if(!c.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(c);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
	}
	@GetMapping("/reporte")
	public ResponseEntity<?> generateReportCareers() {
		List<ReporteCarreraDTO> c=service.generateReport();
		if(!c.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(c);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
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
		CarreraDTO ca=service.deleteCarreraById(id);
		if(ca!=null) {
			Map<String, Object> response = new HashMap<>();
	        response.put("message", "borrado");
	        response.put("carrera", ca);
		    return ResponseEntity.status(HttpStatus.OK).body(response);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
		
	}

	
	@GetMapping()
	public ResponseEntity<?> findAll(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
	}

}
