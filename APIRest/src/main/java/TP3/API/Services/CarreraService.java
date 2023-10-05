package TP3.API.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import DTOS.CarreraDTO;
import TP3.APIRest.entities.Carrera;
import TP3.APIRest.repositories.CarreraRepository;
import jakarta.transaction.Transactional;

@Service
public class CarreraService {
	
	@Autowired
	private CarreraRepository repository;
	
	@Transactional
	public ResponseEntity<Carrera> searchById(@PathVariable Integer id) {
			
			Optional<Carrera> carrera= repository.findById(id);
			
			if(carrera.isPresent()) {
				return ResponseEntity.ok(carrera.get());
			}
			else {
				return ResponseEntity.notFound().build();
			}
		}
	@Transactional
	public Carrera save(Carrera entity) throws Exception {
		 try{
	            return repository.save(entity);
	        }catch (Exception e){
	            throw new Exception(e.getMessage());
	        }
	}
	@Transactional
	public CarreraDTO deleteCarreraById(@PathVariable int id) {
	    try {
	        Optional<Carrera> optionalCarrera = repository.findById(id);

	        if (optionalCarrera.isPresent()) {
	            Carrera carrera = optionalCarrera.get();
	            repository.deleteById(id);
	            CarreraDTO carreraDTO = new CarreraDTO(carrera.getId(),carrera.getNombre(),carrera.getDuracion());
	            return carreraDTO;
	        } else {
	            return null;
	        }
	    } catch (Exception e) {
	        return null;
	    }
	}

	@Transactional
	public List<Carrera> findAll(){
		return new ArrayList<Carrera>(repository.findAll());
	}
}
