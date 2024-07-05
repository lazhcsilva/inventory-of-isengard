package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.dio.domain.model.Item;
import me.dio.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @Operation(summary = "Get all itens")
    @GetMapping
    public ResponseEntity<List<Item>> getAll() {
        return ResponseEntity.ok(itemService.getAll());
    }

    @Operation(summary = "Get item by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.findById(id));
    }

    @Operation(summary = "Insert Item")
    @PostMapping
    public ResponseEntity<Item> insert(@RequestBody Item item) {
        itemService.insert(item);
        return ResponseEntity.ok(item);
    }

    @Operation(summary = "Update item")
    @PutMapping("/{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item item) {
        itemService.update(id, item);
        return ResponseEntity.ok(item);
    }

    @Operation(summary = "Delete item")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        itemService.delete(id);
        return ResponseEntity.ok().build();
    }

}
