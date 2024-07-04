package me.dio.domain.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
