package TP3.APIRest.repositories;


import TP3.APIRest.entities.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;


import TP3.APIRest.entities.Estudiante;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer>{

    @Query("SELECT e FROM Estudiante e WHERE e.nroLibreta = :nro_libreta")
    Estudiante getEstudianteByLibreta(@Param("nro_libreta") int nro_libreta);

    @Query("SELECT e FROM Estudiante e WHERE e.genero = :genero")
    List<Estudiante> getEstudianteByGenero(@Param("genero") String genero);

}
