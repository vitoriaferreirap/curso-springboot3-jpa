package com.vitoriaferreira.curso.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitoriaferreira.curso.entities.User;
import com.vitoriaferreira.curso.services.UserService;

@RestController // indica q classe é um controlador REST
@RequestMapping(value = "/users") // mapeia a URL base para os recursos dessa classe
public class UserResource {

    // UserResource depende do UserService

    @Autowired
    private UserService service;

    @GetMapping // mapeia o método HTTP GET para o endpoint /users
    public ResponseEntity<List<User>> findAll() {// RespondeEntity é uma classe que encapsula a resposta HTTP
        List<User> list = service.findAll(); // Chama o serviço para obter a lista de usuários
        return ResponseEntity.ok().body(list); // Retorna a resposta com o usuário criado
    }

    // endpoint para buscar usuário por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj); // Retorna a resposta com o usuário encontrado
    }

}
