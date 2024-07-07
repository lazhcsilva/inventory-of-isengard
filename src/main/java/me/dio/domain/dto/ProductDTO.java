package me.dio.domain.dto;

public record ProductDTO(Long id,
                         String name,
                         double price,
                         ProductCategoryDTO category) {
}
