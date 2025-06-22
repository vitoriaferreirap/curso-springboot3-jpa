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

    public User delete(Long id) {
        User obj = findById(id);
        repository.deleteById(id);
        return obj;
    }

    public User update(Long id, User obj) {
        User entity = findById(id);
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) { // recebe dados que serão atualizados
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());

    }

}
