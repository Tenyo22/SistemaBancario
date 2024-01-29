package com.empleados.sistemabancario.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class CuentaBancaria implements Serializable {
    @Id
    private String numeroCuenta;
    private Long saldo;

    @ManyToOne
    @JoinColumn(name = "titular_id", referencedColumnName = "id")
    private Titular titular;
    private char status;

}
