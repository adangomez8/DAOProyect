package TP3.APIRest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;


import TP3.APIRest.entities.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{
	
}
