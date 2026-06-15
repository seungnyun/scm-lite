package com.ksn.scmlite.controller;

import com.ksn.scmlite.dto.InventoryRequest;
import com.ksn.scmlite.dto.InventoryResponse;
import com.ksn.scmlite.dto.InventorySearchRequest;
import com.ksn.scmlite.dto.InventoryShortageResponse;
import com.ksn.scmlite.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<InventoryResponse> search(@ModelAttribute InventorySearchRequest request, Pageable pageable){
        return inventoryService.search(request, pageable);
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

    @GetMapping("/shortage")
    public List<InventoryShortageResponse> findShortageInventories(){
        return inventoryService.findShortageInventories();
    }
}
