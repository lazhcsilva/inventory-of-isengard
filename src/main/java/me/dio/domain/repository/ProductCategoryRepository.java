package me.dio.domain.repository;

import me.dio.domain.model.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    boolean existsByName(String name);
    ProductCategory findByName(String name);
}
