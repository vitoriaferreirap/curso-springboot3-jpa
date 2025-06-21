package com.vitoriaferreira.curso.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitoriaferreira.curso.entities.Order;
import com.vitoriaferreira.curso.services.OrderService;

@RestController // indica q classe é um controlador REST
@RequestMapping(value = "/orders") // mapeia a URL base para os recursos dessa classe
public class OrderResource {

    // UserResource depende do OrderService

    @Autowired
    private OrderService service;

    @GetMapping // mapeia o método HTTP GET para o endpoint /users
    public ResponseEntity<List<Order>> findAll() {// RespondeEntity é uma classe que encapsula a resposta HTTP
        List<Order> list = service.findAll(); // Chama o serviço para obter a lista de usuários
        return ResponseEntity.ok().body(list); // Retorna a resposta com o usuário criado
    }

    // endpoint para buscar usuário por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj); // Retorna a resposta com o usuário encontrado
    }

}
