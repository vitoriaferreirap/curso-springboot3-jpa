package com.vitoriaferreira.curso.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    // endpoint para inserir um novo usuário
    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        obj = service.insert(obj);// Chama o serviço (operação insert) para inserir o usuário
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri(); // Cria a URI do novo recurso
        return ResponseEntity.created(uri).body(obj); // Retorna a resposta com o usuário criado
    }

    // endpoint para deletar um usuário
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) { // void - não retorna nenhum body
        service.delete(id); // Chama o serviço (operação delete) para deletar o usuário
        return ResponseEntity.noContent().build(); // Retorna uma resposta sem conteúdo (204 No Content)
    }

    // endpoint para atualizar um usuário
    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
        obj = service.update(id, obj); // Chama o serviço (operação update) para atualizar o usuário
        return ResponseEntity.ok().body(obj); // Retorna a resposta com o usuário atualizado

    }

}
