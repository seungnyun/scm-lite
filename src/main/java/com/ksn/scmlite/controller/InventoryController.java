package com.ksn.scmlite.controller;

import com.ksn.scmlite.entity.Inventory;
import com.ksn.scmlite.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public List<Inventory> findAll(){
        return inventoryService.findAll();
    }

    @GetMapping("/{id}")
    public Inventory findById(@PathVariable Long id){
        return inventoryService.findById(id);
    }

    @PostMapping
    public Inventory save(@RequestBody Inventory inventory){
        return inventoryService.save(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateQuantity(@PathVariable Long id, @RequestBody Inventory inventory){
        return inventoryService.updateQuantity(id, inventory.getQuantity());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        inventoryService.delete(id);
    }
}
