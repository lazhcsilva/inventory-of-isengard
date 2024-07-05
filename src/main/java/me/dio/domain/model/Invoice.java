package me.dio.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String purchaseDate;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }
}
