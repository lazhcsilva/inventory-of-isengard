package me.dio.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Item> items;

    private double totalOrderValue;

    private String dateOrder;

    public Order() {
    }

    public Order(List<Item> items, double totalOrderValue, String dateOrder) {
        this.items = items;
        this.totalOrderValue = totalOrderValue;
        this.dateOrder = dateOrder;
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public double getTotalOrderValue() {
        return totalOrderValue;
    }

    public String getDateOrder() {
        return dateOrder;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void setTotalOrderValue(double totalOrderValue) {
        this.totalOrderValue = totalOrderValue;
    }

    public void setDateOrder(String dateOrder) {
        this.dateOrder = dateOrder;
    }

}
