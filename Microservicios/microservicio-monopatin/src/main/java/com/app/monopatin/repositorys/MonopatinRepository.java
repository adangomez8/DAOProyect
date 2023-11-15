package com.app.monopatin.repositorys;

import com.app.monopatin.entitys.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface MonopatinRepository extends JpaRepository<Monopatin, Integer> {
	
	    @Query("SELECT m.id AS monopatin_id, SIZE(m.id_viaje) AS cantidad_viajes " +
	    		"FROM Monopatin m " +
	    		"GROUP BY m.id " +
	    		"HAVING SIZE(m.id_viaje) >= :cantidad ")
	    
     List<Monopatin> findMonopatinesOrderByCantidadViajes(@Param("cantidad")int cantidad);
	 
	 @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.estado='Disponible'")   
	 Integer getCantMonopatinDisponible();
	 
	 @Query("SELECT COUNT(m) FROM Monopatin m WHERE m.estado='Mantenimiento'")
	 int getCantMonopatinMantenimiento();
}
