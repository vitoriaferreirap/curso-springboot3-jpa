package com.vitoriaferreira.curso.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vitoriaferreira.curso.entities.User;
import com.vitoriaferreira.curso.repositories.UserRepository;

@Configuration // Anotação que indica que essa classe é uma classe de configuração do Spring
@Profile("test") // Anotação que indica que essa configuração é para o perfil"test"
public class TestConfig implements CommandLineRunner {

    // depende do user (injeção de dependência)
    @Autowired // Anotação que indica que o Spring deve injetar uma instância do UserRepository
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2)); // Salva os usuários no banco de dados envia lista
    }

}
