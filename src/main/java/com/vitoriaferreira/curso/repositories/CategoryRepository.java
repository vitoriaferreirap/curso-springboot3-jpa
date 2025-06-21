package com.vitoriaferreira.curso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vitoriaferreira.curso.entities.Category;

// JPA Repository é uma interface que fornece métodos para acessar o banco de dados e trabalhar com user
public interface CategoryRepository extends JpaRepository<Category, Long> {// entidade, tipo do id)

}
