package TP3.API.Services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import DTOS.IcarreraDTO;
import TP3.APIRest.entities.Carrera;
import TP3.APIRest.entities.Estudiante;
import TP3.APIRest.entities.InfoCarrera;
import TP3.APIRest.repositories.InfoCarreraRepository;
import jakarta.transaction.Transactional;

@Service
public class InfoCarreraService {
	@Autowired
	private InfoCarreraRepository repository;
	@Autowired
	private CarreraService servicioCarrera;
	@Autowired
	private EstudianteService servicioEstudiante;
	
	@Transactional
	public InfoCarrera SearchById(@PathVariable Integer id) {
		Optional<InfoCarrera> icarrera= repository.findById(id);
		
		if(icarrera.isPresent()) {
			return icarrera.get();
		}
		else {
			return null;
		}
	}
	@Transactional
	public InfoCarrera persist(@RequestBody IcarreraDTO c) {
		Carrera ca=servicioCarrera.searchById(c.getCarrera());
		Estudiante e=servicioEstudiante.SearchById(c.getEstudiante());
		if((ca!=null)&&(e!=null)) {
			InfoCarrera i=new InfoCarrera(c.isGraduado(),c.getAntiguedad(),ca,e);
			repository.save(i);
			return i;
		}
		
		return null;
	
	}
	
	@Transactional
	public IcarreraDTO delete(@PathVariable Integer id) {
		Optional<InfoCarrera>optionalIcarrera=repository.findById(id);
		
		if(optionalIcarrera.isPresent()) {
			InfoCarrera info=optionalIcarrera.get();
			IcarreraDTO infocDTO=new IcarreraDTO(info.isGraduado(),info.getAntiguedad(),info.getCarrera().getId(),info.getEstudiante().getNroLibreta());
			repository.deleteById(id);
			
	        return infocDTO;
		}
		else {
			return null;
		}
	}
	
	public List<InfoCarrera>getAlls(){
		return repository.findAll();
	}
}
