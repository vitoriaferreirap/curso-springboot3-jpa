package com.vitoriaferreira.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vitoriaferreira.curso.entities.Category;
import com.vitoriaferreira.curso.entities.Order;
import com.vitoriaferreira.curso.entities.OrderItem;
import com.vitoriaferreira.curso.entities.Payment;
import com.vitoriaferreira.curso.entities.Product;
import com.vitoriaferreira.curso.entities.User;
import com.vitoriaferreira.curso.entities.enums.OrderStatus;
import com.vitoriaferreira.curso.repositories.CategoryRepository;
import com.vitoriaferreira.curso.repositories.OrderItemRepository;
import com.vitoriaferreira.curso.repositories.OrderRepository;
import com.vitoriaferreira.curso.repositories.ProductRepository;
import com.vitoriaferreira.curso.repositories.UserRepository;

@Configuration // Anotação que indica que essa classe é uma classe de configuração do Spring
@Profile("test") // Anotação que indica que essa configuração é para o perfil"test"
public class TestConfig implements CommandLineRunner {

    // depende do user (injeção de dependência (injeta repositories))
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    // Método que será executado quando a aplicação for iniciada
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3)); // Salva as categorias no banco de dados envia
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5)); // Salva os produtos no banco de dados envia lista

        // Associa as categorias aos produtos
        p1.getCategories().add(cat2); // Adiciona a categoria Books ao produto
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        // Salva novamente os produtos com as categorias ASSOCIADAS
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

        userRepository.saveAll(Arrays.asList(u1, u2)); // Salva os usuários no banco de dados envia lista
        orderRepository.saveAll(Arrays.asList(o1, o2, o3)); // Salva os pedidos no banco de dados envia lista

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        // associando um pagamento a um pedido
        Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
        // salvar obj independente um para um
        o1.setPayment(pay1); // Associa o pagamento ao pedido
        orderRepository.save(o1); // Salva o pedido com o pagamento associado

    }

}
