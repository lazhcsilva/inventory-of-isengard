package me.dio.service.impl;

import me.dio.controller.handler.BusinessException;
import me.dio.domain.dto.ProductDTO;
import me.dio.domain.model.Product;
import me.dio.domain.model.ProductCategory;
import me.dio.domain.repository.ProductCategoryRepository;
import me.dio.domain.repository.ProductRepository;
import me.dio.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<Product> getAll() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new BusinessException("No record saved.");
        }
        return products;
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new BusinessException("Product not found.");
        }
        return product.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Product findByName(String productName) {
        Optional<Product> product = productRepository.findByName(productName);
        if (product.isEmpty()) {
            throw new BusinessException("This product not found.");
        }
        return product.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void insert(ProductDTO product) {
        if (productRepository.existsByName(product.name())) {
            throw new BusinessException("This product already exists.");
        }
        Product newProduct = getNewProduct(product);
        productRepository.save(newProduct);
    }

    @Override
    public void update(Long id, Product product) {
        Optional<Product> productDB = productRepository.findById(id);
        if (productDB.isEmpty()) {
            throw new BusinessException("ID not found.");
        }
        productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    private Product getNewProduct(ProductDTO product) {
        ProductCategory category = categoryRepository.findByName(product.category().name())
                .orElseGet(() -> categoryRepository.save(new ProductCategory(product.category().name())));

        return new Product(
                product.name(),
                product.price(),
                category);
    }
}
