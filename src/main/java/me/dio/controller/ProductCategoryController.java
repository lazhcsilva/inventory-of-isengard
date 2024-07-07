package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.dio.domain.dto.ProductCategoryDTO;
import me.dio.domain.model.ProductCategory;
import me.dio.service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productCategory")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @Operation(summary = "Get all product category")
    @GetMapping
    public ResponseEntity<Iterable<ProductCategory>> getAll() {
        return ResponseEntity.ok(productCategoryService.getAll());
    }

    @Operation(summary = "Get category by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getById(@PathVariable Long id) {
        return ResponseEntity.ok(productCategoryService.findById(id));
    }

    @Operation(summary = "Get category by name")
    @GetMapping("/getName")
    public ResponseEntity<ProductCategory> getByName(@RequestParam String categoryName) {
        return ResponseEntity.ok(productCategoryService.getByName(categoryName));
    }

    @Operation(summary = "Insert category")
    @PostMapping
    public ResponseEntity<ProductCategoryDTO> insert(@RequestBody ProductCategoryDTO productCategory) {
        productCategoryService.insert(productCategory);
        return ResponseEntity.ok(productCategory);
    }

    @Operation(summary = "Update category")
    @PutMapping("/{id}")
    public ResponseEntity<ProductCategory> update(@PathVariable Long id, @RequestBody ProductCategory productCategory) {
        productCategoryService.update(id, productCategory);
        return ResponseEntity.ok(productCategory);
    }

    @Operation(summary = "Delete category")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productCategoryService.delete(id);
        return ResponseEntity.ok().build();
    }

}
