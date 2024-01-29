package com.empleados.sistemabancario.controllers;

import com.empleados.sistemabancario.entities.CuentaBancaria;
import com.empleados.sistemabancario.models.SaldoModel;
import com.empleados.sistemabancario.models.TransferenciaModel;
import com.empleados.sistemabancario.services.CuentaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuenta")
public class CuentaBancariaController {
    @Autowired
    private CuentaBancariaService cuentaBancariaService;

    @GetMapping("/saldo/{cuenta}")
    private ResponseEntity<SaldoModel> getSaldoByCuenta(@PathVariable String cuenta){
        return cuentaBancariaService.getSaldo(cuenta);
    }

    @PostMapping
    private ResponseEntity<CuentaBancaria> create(@RequestBody CuentaBancaria cuentaBancaria){
        return cuentaBancariaService.create(cuentaBancaria);
    }

    @PutMapping("/deposito/{cuenta}/{monto}")
    private ResponseEntity<SaldoModel> updateSaldo(@PathVariable String cuenta, @PathVariable Long monto){
        return cuentaBancariaService.updateSaldo(cuenta, monto);
    }

    @PutMapping("/retiro/{cuenta}/{monto}")
    private ResponseEntity<SaldoModel> retiroSaldo(@PathVariable String cuenta, @PathVariable Long monto){
        return cuentaBancariaService.retiroSaldo(cuenta, monto);
    }

    @PutMapping("/transferencia/{cuenta1}/{cuenta2}/{monto}")
    private ResponseEntity<TransferenciaModel> trasnferencia(@PathVariable String cuenta1, @PathVariable String cuenta2, @PathVariable Long monto){
        return cuentaBancariaService.transferencia(cuenta1, cuenta2, monto);
    }
}
