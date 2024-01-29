package com.empleados.sistemabancario.repositories;

import com.empleados.sistemabancario.entities.CuentaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaBancariaRepository extends JpaRepository<CuentaBancaria, String> {

    CuentaBancaria findByNumeroCuenta(String numeroCuenta);
}
