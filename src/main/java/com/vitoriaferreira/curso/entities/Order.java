package com.vitoriaferreira.curso.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vitoriaferreira.curso.entities.enums.OrderStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order") // trocando nome tabela
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    // associações muitos para 1
    @ManyToOne
    @JoinColumn(name = "client_id") // nome da coluna que referencia a tabela User (chave estrangeira)
    private User client;

    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>(); // conjunto de itens do pedido

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // associação 1 para 1 (mapea mesmo id para ambas)
    private Payment payment; // atributo associado

    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus); // numero interno convertido
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        if (orderStatus == null) {
            throw new IllegalArgumentException("OrderStatus cannot be null");
        }
        this.orderStatus = orderStatus.getCode(); // converte para numero interno
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Double getTotal() {
        double sum = 0.0;
        for (OrderItem item : items) {
            sum += item.getSubTotal(); // soma o subtotal de cada item
        }
        return sum;
    }

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
        Order other = (Order) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
