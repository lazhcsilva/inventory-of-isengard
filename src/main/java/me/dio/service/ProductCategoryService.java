package me.dio.service;

import me.dio.domain.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getAll();
    ProductCategory findById(Long id);
    void insert(ProductCategory productCategory);
    void update(Long id, ProductCategory productCategory);
    void delete(Long id);

}
