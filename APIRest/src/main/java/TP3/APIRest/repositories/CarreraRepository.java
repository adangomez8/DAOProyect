package TP3.APIRest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import TP3.APIRest.entities.Carrera;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
	
	public ResponseEntity<Carrera> searchById(@PathVariable int id);
	public ResponseEntity<Carrera> persist(Carrera i);
	public ResponseEntity<Carrera> delete(@PathVariable int id);
	public List<Carrera>getAll();

}
