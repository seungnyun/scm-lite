package com.ksn.scmlite.service;

import com.ksn.scmlite.entity.Item;
import com.ksn.scmlite.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public List<Item> findAll(){
        return itemRepository.findAll();
    }

    public Item findbyid(Long id){
        return  itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));
    }

    public Item save(Item item) {
        return itemRepository.save(item);
    }

    public void delete(Long id){
        itemRepository.deleteById(id);
    }

    @Transactional
    public Item update(Long id, Item request){
        Item item = itemRepository.findById(id).orElseThrow(() -> new RuntimeException("Item not found"));

        item.update(request.getItemName(), request.getSafetyStock());

        return item;
    }
}
