package TP3.APIRest.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import TP3.APIRest.entities.Carrera;
import TP3.APIRest.entities.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{
	
	@Query("SELECT e FROM Estudiante e WHERE e.genero = :genre")
	List<Estudiante>getEstudiantePorGenero(@Param("genre")String genre);
	
	 @Query("SELECT e FROM Estudiante e INNER JOIN e.carreras c WHERE (c.carrera.nombre = :career AND e.ciudad = :city)")
     List<Estudiante> getEstudiantesPorCarreraYCiudad(@Param("career") String career, @Param("city") String city);
	 
	 
}
