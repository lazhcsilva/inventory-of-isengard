package me.dio.service;

import me.dio.domain.model.Product;

public interface ProductService {

    Iterable<Product> getAll();
    Product findById(Long id);
    void insert(Product product);
    void update(Long id, Product product);
    void delete(Long id);

}
