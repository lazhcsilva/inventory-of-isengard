package me.dio.service;

import me.dio.domain.dto.ProductDTO;
import me.dio.domain.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product findById(Long id);
    Product findByName(String productName);
    void insert(ProductDTO product);
    void update(Long id, Product product);
    void delete(Long id);

}
