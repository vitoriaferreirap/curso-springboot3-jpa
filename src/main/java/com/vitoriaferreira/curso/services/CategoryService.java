package com.vitoriaferreira.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitoriaferreira.curso.entities.Category;
import com.vitoriaferreira.curso.repositories.CategoryRepository;

//temque ser registrado como componente pois  UserResource depende do UserService
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository; // Injeção de dependência do UserRepository

    public List<Category> findAll() {
        return repository.findAll(); // repassa a chamada para o repositório
    }

    public Category findById(Long id) {
        Optional<Category> obj = repository.findById(id);
        return obj.get();
    }
}
