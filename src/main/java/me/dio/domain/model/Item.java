package me.dio.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    @ManyToOne
    private Product product;

    private double totalVale;

    public Item() {
    }

    public Item(int quantity, Product product, double totalVale) {
        this.quantity = quantity;
        this.product = product;
        this.totalVale = totalVale;
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    public double getTotalVale() {
        return totalVale;
    }

    public void setTotalVale(double totalVale) {
        this.totalVale = totalVale;
    }

}
