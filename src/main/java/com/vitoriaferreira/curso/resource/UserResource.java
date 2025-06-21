package com.vitoriaferreira.curso.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitoriaferreira.curso.entities.User;

@RestController // anotação que indica que essa classe é um controlador REST
@RequestMapping(value = "/users") // mapeia a URL base para os recursos dessa classe
public class UserResource {

    // RespondeEntity é uma classe que encapsula a resposta HTTP
    // O método findAll() deve retornar uma lista de usuários
    @GetMapping // mapeia o método HTTP GET para o endpoint /users
    public ResponseEntity<User> findAll() {
        User u = new User(1L, "Maria", "maria@gmail.com", "000-0000", "123456");
        return ResponseEntity.ok().body(u); // Retorna a resposta com o usuário criado
    }

}
