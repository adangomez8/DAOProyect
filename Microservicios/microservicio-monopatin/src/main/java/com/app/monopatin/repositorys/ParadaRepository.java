package com.app.monopatin.repositorys;

import com.app.monopatin.models.entitys.Parada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParadaRepository extends JpaRepository<Parada, Integer> {
}
