package com.ksn.scmlite.controller;

import com.ksn.scmlite.entity.Warehouse;
import com.ksn.scmlite.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouses")
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public List<Warehouse> findAll(){
        return warehouseService.findAll();
    }

    @GetMapping("/{id}")
    public Warehouse findById(@PathVariable Long id){
        return warehouseService.findById(id);
    }

    @PostMapping
    public Warehouse save(@RequestBody Warehouse warehouse){
        return warehouseService.save(warehouse);
    }

    @DeleteMapping("/{id}")
    public void deleteById(Long id){
        warehouseService.delete(id);
    }

    @PutMapping("/{id}")
    public Warehouse update(@PathVariable Long id, @RequestBody Warehouse warehouse){
        return warehouseService.update(id, warehouse);
    }
}
