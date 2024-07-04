package me.dio.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_order")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<Item> itens;
    @OneToOne
    private Invoice invoice;

    public Long getId() {
        return id;
    }

    public List<Item> getItem() {
        return itens;
    }

    public Invoice getInvoice() {
        return invoice;
    }
}
