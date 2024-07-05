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
    private List<Item> itens;

    @OneToOne
    private Invoice invoice;

    private String dateOrder;

    public Long getId() {
        return id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public List<Item> getItens() {
        return itens;
    }

    public String getDate() {
        return dateOrder;
    }
}
