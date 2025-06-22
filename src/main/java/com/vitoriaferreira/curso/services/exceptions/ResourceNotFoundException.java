package com.vitoriaferreira.curso.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id " + id); // SUPER chama construtor da superclasse RuntimeException
    }

}