package me.dio.service.impl;

import me.dio.controller.handler.BusinessException;
import me.dio.domain.dto.ItemDTO;
import me.dio.domain.dto.OrderDTO;
import me.dio.domain.model.Item;
import me.dio.domain.model.Order;
import me.dio.domain.model.Product;
import me.dio.domain.repository.OrderRepository;
import me.dio.service.ItemService;
import me.dio.service.OrderService;
import me.dio.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final ItemService itemService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            ProductService productService,
                            ItemService itemService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.itemService = itemService;
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            throw new BusinessException("No orders saved.");
        }
        return orders;
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new BusinessException("This order not found.");
        }
        return order.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void insert(OrderDTO orderDTO) {

        List<Item> items = new ArrayList<>();
        double totalOrderValue = 0.0;

        for (ItemDTO itemDTO : orderDTO.items()) {
            String productName = itemDTO.productName();
            int quantity = itemDTO.quantity();

            Product product = productService.findByName(productName);
            if (product == null) {
                throw new BusinessException("Product not found: " + productName);
            }

            double itemTotalValue = product.getPrice() * quantity;

            Item item = new Item(quantity, product, itemTotalValue);
            items.add(item);
            totalOrderValue += itemTotalValue;

        }

        Order order = new Order(items, totalOrderValue, orderDTO.dateOrder());

        orderRepository.save(order);

    }

    @Override
    public void update(Long id, Order order) {
        try {
            Optional<Order> orderBD = orderRepository.findById(id);
            if (orderBD.isEmpty()) {
                throw new BusinessException("ID not found.");
            }

            Order existingOrder = orderBD.get();
            List<Item> updatedItems = new ArrayList<>();
            double newTotalOrderValue  = 0.0;
            //order.getTotalOrderValue();
            for (Item updatedItem : order.getItems()) {
                Product product = productService.findByName(updatedItem.getProduct().getName());
                if (product == null) {
                    throw new BusinessException("Product not found: " + updatedItem.getProduct().getName());
                }

                double itemTotalValue = product.getPrice() * updatedItem.getQuantity();
                updatedItem.setTotalVale(itemTotalValue);
                updatedItems.add(updatedItem);
                newTotalOrderValue += itemTotalValue;

            }

            existingOrder.getItems().clear();
            existingOrder.getItems().addAll(updatedItems);
            existingOrder.setTotalOrderValue(newTotalOrderValue);
            existingOrder.setDateOrder(order.getDateOrder());

            orderRepository.save(existingOrder);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @Override
    public void delete(Long id) {
        Order order = findById(id);
        orderRepository.delete(order);
    }
}
