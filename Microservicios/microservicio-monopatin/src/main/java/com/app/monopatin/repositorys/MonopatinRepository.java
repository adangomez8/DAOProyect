package com.app.monopatin.repositorys;

import com.app.monopatin.entitys.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonopatinRepository extends JpaRepository<Monopatin, Integer> {
}
