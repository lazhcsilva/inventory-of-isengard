package me.dio.service.impl;

import me.dio.domain.model.Item;
import me.dio.domain.repository.ItemRepository;
import me.dio.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = itemRepository.findAll();
        if (items.isEmpty()) {
            throw new RuntimeException("No item saved.");
        }
        return items;
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public void insert(Item item) {
        itemRepository.save(item);
    }

    @Override
    public void update(Long id, Item item) {
        Optional<Item> itemBD = itemRepository.findById(id);
        if (itemBD.isEmpty()) {
            throw new RuntimeException("ID not Found.");
        }
        itemRepository.save(item);
    }

    @Override
    public void delete(Long id) {
        Item item = findById(id);
        itemRepository.delete(item);
    }
}
