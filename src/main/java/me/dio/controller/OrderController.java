package me.dio.controller;

import io.swagger.v3.oas.annotations.Operation;
import me.dio.domain.model.Order;
import me.dio.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "Get all orders")
    @GetMapping
    public ResponseEntity<List<Order>> findAll() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @Operation(summary = "Get order by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @Operation(summary = "Insert new order")
    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order order) {
        orderService.insert(order);
        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Update order")
    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order) {
        orderService.update(id, order);
        return ResponseEntity.ok(order);
    }

    @Operation(summary = "Delete order")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.ok().build();
    }

}
