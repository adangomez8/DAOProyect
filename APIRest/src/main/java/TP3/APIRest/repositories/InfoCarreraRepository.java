package TP3.APIRest.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import TP3.APIRest.entities.InfoCarrera;

@Repository
public interface InfoCarreraRepository extends JpaRepository<InfoCarrera, Integer>{
	
	public ResponseEntity<InfoCarrera> searchById(@PathVariable int id);
	public ResponseEntity<InfoCarrera> persist(InfoCarrera i);
	public ResponseEntity<InfoCarrera> delete(@PathVariable int id);
	public List<InfoCarrera>getAll();
}
