package com.vitoriaferreira.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitoriaferreira.curso.entities.User;
import com.vitoriaferreira.curso.repositories.UserRepository;

//temque ser registrado como componente pois  UserResource depende do UserService
@Service
public class UserService {

    @Autowired
    private UserRepository repository; // Injeção de dependência do UserRepository

    public List<User> findAll() {
        return repository.findAll(); // repassa a chamada para o repositório
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.get();
    }

    // operações
    public User insert(User obj) {
        return repository.save(obj);
    }

}
