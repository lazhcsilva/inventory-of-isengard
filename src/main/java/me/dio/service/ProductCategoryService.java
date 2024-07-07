package me.dio.service;

import me.dio.domain.dto.ProductCategoryDTO;
import me.dio.domain.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getAll();
    ProductCategory findById(Long id);
    ProductCategory getByName(String categoryName);
    void insert(ProductCategoryDTO productCategory);
    void update(Long id, ProductCategory productCategory);
    void delete(Long id);

}
