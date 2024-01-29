package com.empleados.sistemabancario.controllers;

import com.empleados.sistemabancario.entities.Titular;
import com.empleados.sistemabancario.services.TitularService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/titular")
public class TitularController {
    @Autowired
    private TitularService titularService;

    @GetMapping("/{name}")
    private ResponseEntity<Titular> getTitular(@PathVariable String name){
        return titularService.getTitular(name);
    }

    @PostMapping
    private ResponseEntity<Titular> create(@RequestBody Titular titular){
        return titularService.create(titular);
    }
}
