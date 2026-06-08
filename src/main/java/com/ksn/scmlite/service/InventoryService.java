package com.ksn.scmlite.service;

import com.ksn.scmlite.entity.Inventory;
import com.ksn.scmlite.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<Inventory> findAll(){
        return inventoryRepository.findAll();
    }

    public Inventory findById(Long id){
        return inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    public Inventory save(Inventory inventory){
        return inventoryRepository.save(inventory);
    }

    @Transactional
    public Inventory updateQuantity(Long id, Integer quantity){
        Inventory inventory = inventoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Inventory not found"));

        inventory.setQuantity(quantity);

        return inventory;
    }

    public void delete(Long id){
        inventoryRepository.deleteById(id);
    }
}
