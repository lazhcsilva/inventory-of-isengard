package me.dio.service.impl;

import me.dio.domain.model.Order;
import me.dio.domain.repository.OrderRepository;
import me.dio.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new RuntimeException("No record saved");
        }
        return orders;
    }

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void insert(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void update(Long id, Order order) {
        Optional<Order> orderDB = orderRepository.findById(id);
        if (orderDB.isEmpty()) {
            throw new RuntimeException("ID not Found");
        }
        orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);
    }
}
