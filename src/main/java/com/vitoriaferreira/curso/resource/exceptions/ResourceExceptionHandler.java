package com.vitoriaferreira.curso.resource.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.vitoriaferreira.curso.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice // Anotação que indica que essa classe irá tratar exceções de recursos
public class ResourceExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) // tratar a exceção ResourceNotFoundException
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND; // Define o status HTTP como NOT_FOUND (404)
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err); // Retorna a resposta com o erro formatado
    }

    @ExceptionHandler(Exception.class) // Tratar todas as outras exceções
    public ResponseEntity<StandardError> database(Exception e, HttpServletRequest request) {
        String error = "Database error";
        HttpStatus status = HttpStatus.BAD_REQUEST; // Define o status HTTP como BAD_REQUEST (400)
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err); // Retorna a resposta com o erro formatado
    }

}
