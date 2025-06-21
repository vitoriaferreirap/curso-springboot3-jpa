package com.vitoriaferreira.curso.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//Serializeble = interface para que os obj pos possam ser convertidos em cadeia de  bytes

@Entity // JPA mapea a classe User para uma tabela no banco de dados
@Table(name = "tb_user") // nome da tabela no banco de dados
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id // JPA identifica o atributo id como a chave primária da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento id
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String password;

    @OneToMany(mappedBy = "client") // associação um para muitos com a classe Order
    private List<Order> orders = new ArrayList<>(); // associação com a classe Order e inicializa a lista

    public User() {
    }

    public User(Long id, String name, String email, String phone, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() { // apenas get para listas(colecoes)
        return orders;
    }

    // comparação de objetos
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
