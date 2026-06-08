package com.ksn.scmlite.controller;

import com.ksn.scmlite.entity.Item;
import com.ksn.scmlite.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> findAll(){
        return itemService.findAll();
    }

    @GetMapping("/{id}")
    public Item findById(@PathVariable Long id){
        return itemService.findbyid(id);
    }

    @PostMapping
    public Item save(@RequestBody Item item) {
        return itemService.save(item);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Long id){
        itemService.delete(id);
    }

    @PutMapping("/{id}")
    public Item update(@PathVariable Long id, @RequestBody Item item){
        return itemService.update(id, item);
    }

}
