package TP3.APIRest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import TP3.APIRest.entities.Estudiante;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{
	
	public ResponseEntity<Estudiante> SearchById(@PathVariable int id);
	public ResponseEntity<Estudiante> persist(@RequestBody Estudiante i);
	public ResponseEntity<Estudiante> deleteById(@PathVariable int id);
	public List<Estudiante>getAll();
}
