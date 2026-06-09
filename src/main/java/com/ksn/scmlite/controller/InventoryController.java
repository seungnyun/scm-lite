package com.ksn.scmlite.controller;

import com.ksn.scmlite.dto.InventoryRequest;
import com.ksn.scmlite.dto.InventoryResponse;
import com.ksn.scmlite.dto.InventorySearchRequest;
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
    public List<InventoryResponse> findAll(){
        return inventoryService.findAll();
    }

    @GetMapping("/{id}")
    public InventoryResponse findById(@PathVariable Long id){
        return inventoryService.findById(id);
    }

    @GetMapping("/search")
    public List<InventoryResponse> search(@ModelAttribute InventorySearchRequest request){
        return inventoryService.search(request);
    }

    @PostMapping
    public InventoryResponse save(@RequestBody InventoryRequest request){
        return inventoryService.save(request);
    }

    @PutMapping("/{id}")
    public InventoryResponse update(@PathVariable Long id, @RequestBody InventoryRequest request){
        return inventoryService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        inventoryService.delete(id);
    }
}
