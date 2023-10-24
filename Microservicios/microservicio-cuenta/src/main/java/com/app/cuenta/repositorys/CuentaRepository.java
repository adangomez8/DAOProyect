package com.app.cuenta.repositorys;

import com.app.cuenta.models.entitys.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, Integer> {
}
