package com.empleados.sistemabancario.models;

import lombok.Data;

@Data
public class ExceptionModel {
    private String mensaje;

    public ExceptionModel(String mensaje) {
        this.mensaje = mensaje;
    }
}
