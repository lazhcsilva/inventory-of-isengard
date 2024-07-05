package me.dio.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
