package com.vitoriaferreira.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.vitoriaferreira.curso.entities.User;
import com.vitoriaferreira.curso.repositories.UserRepository;
import com.vitoriaferreira.curso.services.exceptions.DatabaseException;
import com.vitoriaferreira.curso.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

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
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    // operações
    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User obj) {
        try {
            User entity = findById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) { // recebe dados que serão atualizados
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());

    }

}
