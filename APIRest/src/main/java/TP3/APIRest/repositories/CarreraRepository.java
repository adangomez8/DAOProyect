package TP3.APIRest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;


import TP3.APIRest.entities.Carrera;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {


    @Query("SELECT c FROM Carrera c WHERE SIZE(c.estudiantes) >0 ORDER BY SIZE(c.estudiantes)")
    List<Carrera> getCarrerasOrderByCantInscriptos();

}
