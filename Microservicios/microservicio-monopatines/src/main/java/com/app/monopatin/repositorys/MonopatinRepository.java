package com.app.monopatin.repositorys;

import com.app.monopatin.models.entitys.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonopatinRepository extends JpaRepository<Monopatin, Integer> {
}
