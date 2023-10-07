package TP3.APIRest.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import DTOS.CarreraDTO;
import DTOS.ReporteCarreraDTO;
import TP3.APIRest.entities.Carrera;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
	@Query("SELECT c " +
		       "FROM Carrera c " +
		       "WHERE c.infoCarreras IS NOT EMPTY " +
		       "ORDER BY SIZE(c.infoCarreras) DESC")
	List<Carrera>findCarrerasConEsutdiantesOrdenadas();
	
    @Query("SELECT " +
            "c.nombre AS nombreCarrera, " +
            "ic.antiguedad AS año, " +
            "SUM(CASE WHEN ic.graduado = true THEN 1 ELSE 0 END) AS egresados, " +
            "SUM(CASE WHEN ic.graduado = false THEN 1 ELSE 0 END) AS inscriptos " +
            "FROM Carrera c " +
            "LEFT JOIN c.infoCarreras ic " +
            "GROUP BY c.id, c.nombre, ic.antiguedad " +
            "ORDER BY c.nombre ASC, ic.antiguedad ASC")
    List<Object[]> generateCarrerasReport();
}
