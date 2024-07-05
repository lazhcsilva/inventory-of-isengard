package me.dio.service;

import me.dio.domain.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAll();
    Order findById(Long id);
    void insert(Order order);
    void update(Long id, Order order);
    void delete(Long id);

}
