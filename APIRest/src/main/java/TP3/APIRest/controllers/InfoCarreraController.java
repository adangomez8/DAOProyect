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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import DTOS.IcarreraDTO;
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
	private EstudianteRepository erepository;
	@Autowired
	private CarreraRepository crepository;
	@Autowired
	private InfoCarreraRepository repository;
	
	public InfoCarreraController(InfoCarreraRepository repository) {
		this.repository=repository;
	}
	
	@GetMapping("/icarrera/{id}")
	public ResponseEntity<InfoCarrera> SearchById(@PathVariable Integer id) {
		
		Optional<InfoCarrera> icarrera= repository.findById(id);
		
		if(icarrera.isPresent()) {
			return ResponseEntity.ok(icarrera.get());
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/icarrera")
	public ResponseEntity<InfoCarrera> persist(@RequestBody IcarreraDTO c) {
		System.out.println(c);
		if((erepository.existsById(c.getEstudiante()))&&(crepository.existsById(c.getCarrera()))) {
			Optional<Estudiante> e=erepository.findById(c.getEstudiante());
			Optional<Carrera> ca=crepository.findById(c.getCarrera());
			if(e.isPresent()&&ca.isPresent()) {
				InfoCarrera i=new InfoCarrera(c.isGraduado(),c.getAntiguedad(),ca.get(),e.get());
				repository.save(i);
				return ResponseEntity.ok().build();
			}
			else {
				ResponseEntity.notFound().build();
			}
		}
		else {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/icarrera/{id}")
	public ResponseEntity<InfoCarrera> delete(@PathVariable Integer id) {
		
		if(repository.existsById(id)) {
			repository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/icarrera")
	public List<InfoCarrera>getAlls(){
		return repository.findAll();
	}

}
