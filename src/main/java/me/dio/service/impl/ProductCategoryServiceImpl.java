package me.dio.service.impl;

import me.dio.domain.model.ProductCategory;
import me.dio.domain.repository.ProductCategoryRepository;
import me.dio.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> category = productCategoryRepository.findAll();
        if (category.isEmpty()) {
            throw new RuntimeException("No record saved.");
        }
        return category;
    }

    @Override
    public ProductCategory findById(Long id) {
        return productCategoryRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void insert(ProductCategory productCategory) {
        if (productCategoryRepository.existsByName(productCategory.getName())) {
            throw new IllegalArgumentException("This category already exists");
        }
        productCategoryRepository.save(productCategory);
    }

    @Override
    public void update(Long id, ProductCategory productCategory) {
        ProductCategory result = productCategoryRepository.findByName(productCategory.getName());
        if (result != null && !Objects.equals(result.getId(), id)) {
            throw new IllegalArgumentException("There is a product registered with this name");
        }
        productCategoryRepository.save(productCategory);
    }

    @Override
    public void delete(Long id) {
        ProductCategory category = findById(id);
        productCategoryRepository.delete(category);
    }
}
