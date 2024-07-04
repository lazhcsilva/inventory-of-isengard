package me.dio.service.impl;

import me.dio.domain.model.Product;
import me.dio.domain.repository.ProductCategoryRepository;
import me.dio.domain.repository.ProductRepository;
import me.dio.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              ProductCategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<Product> getAll() {
        Iterable<Product> products = productRepository.findAll();
        if (!products.iterator().hasNext()) {
            throw new RuntimeException("No record saved.");
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void insert(Product product) {
        if (productRepository.existsByName(product.getName())) {
            throw new IllegalArgumentException("This product already exists.");
        }
        if (product.getCategories().getId() == null) {
            categoryRepository.save(product.getCategories());
        }
        productRepository.save(product);
    }

    @Override
    public void update(Long id, Product product) {
        Optional<Product> productDB = productRepository.findById(id);
        if (productDB.isEmpty()) {
            throw new RuntimeException("ID not found.");
        }
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }
}
