package com.vitoriaferreira.curso.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitoriaferreira.curso.entities.Product;
import com.vitoriaferreira.curso.services.ProductService;

@RestController // indica q classe é um controlador REST
@RequestMapping(value = "/products") // mapeia a URL base para os recursos dessa classe
public class ProductResource {

    // UserResource depende do OrderService

    @Autowired
    private ProductService service;

    @GetMapping // mapeia o método HTTP GET para o endpoint /users
    public ResponseEntity<List<Product>> findAll() {// RespondeEntity é uma classe que encapsula a resposta HTTP
        List<Product> list = service.findAll(); // Chama o serviço para obter a lista de usuários
        return ResponseEntity.ok().body(list); // Retorna a resposta com a lista de usuários
    }

    // endpoint para buscar usuário por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        Product obj = service.findById(id);
        return ResponseEntity.ok().body(obj); // Retorna a resposta com o usuário encontrado
    }

}
