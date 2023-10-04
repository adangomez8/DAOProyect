package TP3.APIRest.controllers;

import java.util.List;
import java.util.Optional;

import TP3.APIRest.services.CarreraService;
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

import TP3.APIRest.entities.Carrera;
import TP3.APIRest.repositories.CarreraRepository;

@RestController
@RequestMapping("carreras")
public class CarreraController {
	
	@Autowired
	private CarreraService carreraService;
	
	public CarreraController(CarreraService carreraService) {
		this.carreraService=carreraService;
	}
	
	@GetMapping("/byId/{id}")
	public ResponseEntity<?> SearchById(@PathVariable Integer id) throws Exception {

		try{
			return ResponseEntity.status(HttpStatus.OK).body(carreraService.findById(id));
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{Error. No se encontro el estudiante buscado}");
		}
	}
	
	@PostMapping("/carrera")
	public ResponseEntity<?> persist(@RequestBody Carrera c) {
		try{
			return ResponseEntity.status(HttpStatus.OK).body(carreraService.save(c));
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. No se pudo insertar, complete los datos correctamente");
		}
	}
	
	@DeleteMapping("/byId/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {

		try{
			carreraService.deleteById(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Se borró la carrera con ID "+id);
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error. no se pudo eliminar intente nuevamente.}");
		}
	}
	
	@GetMapping("")
	public ResponseEntity<?>findAll(){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(carreraService.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error. Por favor intente más tarde.");
		}
	}

	@GetMapping("/byCantInscriptos")
	public ResponseEntity<?> getCarrerasOrderByInscriptos() {
		try{
			return ResponseEntity.status(HttpStatus.OK).body(carreraService.getCarrerasOrderByInscriptos());
		}catch (Exception e){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{Error. No se encontro la carrera buscada}");
		}
	}

}
