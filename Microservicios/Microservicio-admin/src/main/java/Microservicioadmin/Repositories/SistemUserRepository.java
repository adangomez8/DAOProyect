package Microservicioadmin.Repositories;

import Microservicioadmin.Entities.SistemUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SistemUserRepository extends JpaRepository<SistemUser,Integer> {
    Optional<SistemUser> findByUsername(String username);
}
