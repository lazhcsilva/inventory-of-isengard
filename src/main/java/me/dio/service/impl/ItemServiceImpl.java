package me.dio.service.impl;

import me.dio.controller.handler.BusinessException;
import me.dio.domain.dto.ItemDTO;
import me.dio.domain.model.Item;
import me.dio.domain.model.Product;
import me.dio.domain.repository.ItemRepository;
import me.dio.service.ItemService;
import me.dio.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ProductService productService;

    public ItemServiceImpl(ItemRepository itemRepository, ProductService productService) {
        this.itemRepository = itemRepository;
        this.productService = productService;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty()) {
            throw new BusinessException("No items saved.");
        }
        return items;
    }

    @Override
    public Item findById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isEmpty()) {
            throw new BusinessException("Item not found.");
        }
        return item.orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void insert(Item item) {
        Product product = productService.findByName(item.getProduct().getName());
        if (product == null) {
            throw new BusinessException("This product not found.");
        }
        if (product.getPrice() != item.getProduct().getPrice()) {
            throw new BusinessException("This price is incorrect.");
        }
        double total = calcTotal(item.getProduct().getPrice(), item.getQuantity());
        item.setTotalVale(total);
        itemRepository.save(item);
    }

    @Override
    public void update(Long id, Item item) {
        Optional<Item> itemDB = itemRepository.findById(id);
        if (itemDB.isEmpty()) {
            throw new BusinessException("Id not found.");
        }
        double total = calcTotal(item.getProduct().getPrice(), item.getQuantity());
        item.setTotalVale(total);
        itemRepository.save(item);
    }

    @Override
    public void delete(Long id) {
        Item item = findById(id);
        itemRepository.delete(item);
    }

    private double calcTotal(double value, int quantity) {
        return value * quantity;
    }
}
