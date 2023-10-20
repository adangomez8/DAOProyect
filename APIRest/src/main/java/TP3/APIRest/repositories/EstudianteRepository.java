package TP3.APIRest.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import TP3.APIRest.entities.Carrera;
import TP3.APIRest.entities.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{
	@Query("SELECT e FROM Estudiante e " +
		       "LEFT JOIN e.carreras c " +
		       "WHERE (:carrera IS NULL OR c.carrera.nombre = :carrera) " +
		       "AND (:ciudad IS NULL OR e.ciudad = :ciudad) " +
		       "AND (:genre IS NULL OR e.genero = :genre)")
	List<Estudiante>findAll(@Param("carrera")String carrera,@Param("ciudad")String ciudad,@Param("genre")String genre);

	@Query("SELECT e, c FROM Estudiante  e " +
			"LEFT JOIN e.carreras c " +
			"WHERE (:ciudad IS NULL OR e.ciudad = :ciudad)" +
			"AND (:carrera IS NULL OR c.carrera.nombre = :carrera) " +
			"AND (:genero IS NULL OR e.genero = :genero)")
	List<Estudiante>findAllParams(@Param("ciudad") String ciudad,
								  @Param("genero") String genero,
								  @Param("carrera") String carrera);



}
