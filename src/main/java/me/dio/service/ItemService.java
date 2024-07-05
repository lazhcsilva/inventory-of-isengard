package me.dio.service;

import me.dio.domain.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> getAll();
    Item findById(Long id);
    void insert(Item item);
    void update(Long Id, Item item);
    void delete(Long id);

}
