package com.empleados.sistemabancario.services;

import com.empleados.sistemabancario.entities.Titular;
import com.empleados.sistemabancario.exceptions.CustomException;
import com.empleados.sistemabancario.repositories.TitularRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TitularService {
    @Autowired
    private TitularRepository titularRepository;

    private Titular getByName(String name){
        return titularRepository.findByName(name);
    }

    public ResponseEntity<Titular> getTitular(String name){
        Titular titular = getByName(name);
        if(titular == null){
            throw new CustomException("No existe el titular!");
        }
        return new ResponseEntity<>(titular, HttpStatus.OK);
    }

    public ResponseEntity<Titular> create(Titular titular){
        Titular titular1 = getByName(titular.getName());
        if (titular1 != null){
            throw new CustomException("Ya hay un titular!");
        }
        titular.setStatus('1');
        titularRepository.save(titular);
        return new ResponseEntity<>(titular, HttpStatus.CREATED);
    }
}
