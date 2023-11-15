package com.MicroservicioViaje.repositories;

import com.MicroservicioViaje.entities.Viaje;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {

	
	@Query("SELECT SUM(v.precio) FROM Viaje v"+
			"WHERE YEAR(v.fecha_fin)= :anio"+
			"AND MONTH(v.fecha_fin) BETWEEN :mesIni AND :mesFin")
	static
	double getRecaudacion(@Param("anio") Date anio,@Param("mesIni")Date mesIni,@Param("mesFin")Date mesFin) {
		// TODO Auto-generated method stub
		return 0;
	}
}
