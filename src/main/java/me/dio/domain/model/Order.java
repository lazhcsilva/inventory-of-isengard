package me.dio.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Product> product;
    @OneToOne
    private Invoice invoice;

    public Long getId() {
        return id;
    }

    public List<Product> getProduct() {
        return product;
    }

    public Invoice getInvoice() {
        return invoice;
    }
}
