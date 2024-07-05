package me.dio.service;

import me.dio.domain.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product findById(Long id);
    void insert(Product product);
    void update(Long id, Product product);
    void delete(Long id);

}
