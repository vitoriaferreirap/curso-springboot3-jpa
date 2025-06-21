package com.vitoriaferreira.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitoriaferreira.curso.entities.Order;
import com.vitoriaferreira.curso.repositories.OrderRepository;

//temque ser registrado como componente pois  UserResource depende do UserService
@Service
public class OrderService {

    @Autowired
    private OrderRepository repository; // Injeção de dependência do UserRepository

    public List<Order> findAll() {
        return repository.findAll(); // repassa a chamada para o repositório
    }

    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.get();
    }
}
