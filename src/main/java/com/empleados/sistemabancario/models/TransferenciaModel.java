package com.empleados.sistemabancario.models;

import lombok.Data;

@Data
public class TransferenciaModel {
    private String numeroCuenta1;
    private Long saldo1;
    private String NumeroCuenta2;
    private Long saldo2;
}
