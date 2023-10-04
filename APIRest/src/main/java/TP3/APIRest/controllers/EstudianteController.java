package TP3.APIRest.controllers;

import java.util.List;
import java.util.Optional;

import TP3.APIRest.services.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import TP3.APIRest.entities.Estudiante;
import TP3.APIRest.repositories.EstudianteRepository;

@RestController
@RequestMapping("api/estudiante")
public class EstudianteController {
	
	@Autowired
	private EstudianteService estudianteService;
	
	public EstudianteController(EstudianteService estudianteService) {
		this.estudianteService=estudianteService;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> SearchById(@PathVariable Integer id) {
		try{
			return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findById(id));
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{Error. No se encontro el estudiante buscado}");
		}
	}
	
	@PostMapping("")
	public ResponseEntity<?> persist(@RequestBody Estudiante est) {
		try{
			return ResponseEntity.status(HttpStatus.OK).body(estudianteService.save(est));
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo insertar, complete los datos correctamente");
		}
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id,@RequestBody Estudiante entity){
		try{
			return ResponseEntity.status(HttpStatus.OK).body(estudianteService.save(entity));
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo editar, complete los datos correctamente.");
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable Integer id) {
		try{
			estudianteService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Se borró el estudiante con ID "+id);
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. no se pudo eliminar intente nuevamente.}");
		}

	}

	@GetMapping("")
	public ResponseEntity<?> findAll(){
		try{
			return ResponseEntity.status(HttpStatus.OK).body(estudianteService.findAll());
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Por favor intente más tarde.");
		}
	}

}
