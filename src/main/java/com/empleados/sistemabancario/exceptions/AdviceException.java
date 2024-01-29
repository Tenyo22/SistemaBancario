package com.empleados.sistemabancario.exceptions;

import com.empleados.sistemabancario.models.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceException {

    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ExceptionModel> exceptionModel(CustomException customException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionModel(customException.getMessage()));
    }
}
