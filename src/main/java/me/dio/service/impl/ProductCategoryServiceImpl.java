package me.dio.service.impl;

import me.dio.controller.handler.BusinessException;
import me.dio.domain.dto.ProductCategoryDTO;
import me.dio.domain.model.ProductCategory;
import me.dio.domain.repository.ProductCategoryRepository;
import me.dio.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

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
        Optional<ProductCategory> category = productCategoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new BusinessException("Category not found.");
        }
        return category.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public ProductCategory getByName(String categoryName) {
        Optional<ProductCategory> category = productCategoryRepository.findByName(categoryName);
        if (category.isEmpty()) {
            throw new BusinessException("Category not found.");
        }
        return category.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void insert(ProductCategoryDTO productCategory) {
        if (productCategoryRepository.existsByName(productCategory.name())) {
            throw new BusinessException("This category already exists");
        }
        ProductCategory category = new ProductCategory(productCategory.name());
        productCategoryRepository.save(category);
    }

    @Override
    public void update(Long id, ProductCategory productCategory) {
        Optional<ProductCategory> category = productCategoryRepository.findById(id);
        if (category.isEmpty()) {
            throw new BusinessException("This category already exists");
        }
        productCategoryRepository.save(productCategory);
    }

    @Override
    public void delete(Long id) {
        ProductCategory category = findById(id);
        productCategoryRepository.delete(category);
    }
}
