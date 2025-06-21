package com.vitoriaferreira.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitoriaferreira.curso.entities.Order;

// JPA Repository é uma interface que fornece métodos para acessar o banco de dados e trabalhar com user
public interface OrderRepository extends JpaRepository<Order, Long> {// entidade, tipo do id)

}
