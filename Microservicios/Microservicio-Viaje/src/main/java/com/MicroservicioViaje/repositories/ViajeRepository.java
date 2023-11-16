package com.MicroservicioViaje.repositories;

import com.MicroservicioViaje.entities.Viaje;

import jakarta.transaction.Transactional;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ViajeRepository extends JpaRepository<Viaje, Integer> {

	
	@Query("SELECT SUM((v.precio * v.km_recorridos)+ v.tarifa_extra) FROM Viaje v "+
			"WHERE YEAR(v.fecha_fin)= :anio "+
			"AND MONTH(v.fecha_fin) BETWEEN :mesIni AND :mesFin ")
	static
	double getRecaudacion(@Param("anio") Date anio,@Param("mesIni")Date mesIni,@Param("mesFin")Date mesFin) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Transactional
	@Modifying
	@Query("UPDATE Viaje v SET v.precio= :precio WHERE v.fecha_inicio>=:fecha")
	void updatePrecio(@Param("precio") double precio, @Param("fecha") Date fecha);
	
	
}
