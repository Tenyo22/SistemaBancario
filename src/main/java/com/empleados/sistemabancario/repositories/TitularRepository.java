package com.empleados.sistemabancario.repositories;

import com.empleados.sistemabancario.entities.Titular;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface TitularRepository extends JpaRepository<Titular, Long> {

    Titular findByName(String name);
}
