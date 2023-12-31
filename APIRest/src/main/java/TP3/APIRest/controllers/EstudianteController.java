package TP3.APIRest.controllers;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import DTOS.EstudianteDTO;
import TP3.API.Services.EstudianteService;
import TP3.APIRest.entities.Estudiante;

@RestController
@RequestMapping("api/estudiante")
public class EstudianteController {
	
	@Autowired
	private EstudianteService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<?> SearchById(@PathVariable int id) {
		EstudianteDTO e=service.SearchById(id);
		if(e!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(e);
		}
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron resultados");

		}
		
	}

	@PostMapping()
	public ResponseEntity<?> persist(@RequestBody Estudiante e) {
		EstudianteDTO estudiante=service.persist(e);
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
	public ResponseEntity<?>findAll(@RequestParam(name = "carrera",required = false) String carrera,
									@RequestParam(name="ciudad", required=false) String ciudad,
									@RequestParam(name="genre",required=false) String genre,
									@RequestParam(name="orderby", required=false)String orderby){
		List<EstudianteDTO>e=service.findAll(carrera,ciudad,genre,orderby);
		if(e!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(e);
		}
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("no encontrado");
	}

}
