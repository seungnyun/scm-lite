package com.ksn.scmlite.service;

import com.ksn.scmlite.dto.InventoryRequest;
import com.ksn.scmlite.dto.InventoryResponse;
import com.ksn.scmlite.entity.Inventory;
import com.ksn.scmlite.entity.Item;
import com.ksn.scmlite.entity.Warehouse;
import com.ksn.scmlite.repository.InventoryRepository;
import com.ksn.scmlite.repository.ItemRepository;
import com.ksn.scmlite.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;

    public List<InventoryResponse> findAll(){
        return inventoryRepository.findAll().stream().map(this::toResponse).toList();
    }

    public InventoryResponse findById(Long id){
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow();

        return toResponse(inventory);
    }

    public InventoryResponse save(InventoryRequest request){
        Item item = itemRepository.findById(request.itemId()).orElseThrow();

        Warehouse warehouse = warehouseRepository.findById(request.warehouseId()).orElseThrow();

        Inventory inventory = new Inventory();
        inventory.setItem(item);
        inventory.setWarehouse(warehouse);
        inventory.setQuantity(request.quantity());

        Inventory savedInventory = inventoryRepository.save(inventory);

        return toResponse(savedInventory);
    }

    @Transactional
    public InventoryResponse update(Long id, InventoryRequest request){
        Inventory inventory = inventoryRepository.findById(id).orElseThrow();

        Item item = itemRepository.findById(request.itemId()).orElseThrow();

        Warehouse warehouse = warehouseRepository.findById(request.warehouseId()).orElseThrow();

        inventory.setItem(item);
        inventory.setWarehouse(warehouse);
        inventory.setQuantity(request.quantity());

        return toResponse(inventory);
    }

    public void delete(Long id){
        inventoryRepository.deleteById(id);
    }

    private InventoryResponse toResponse(Inventory inventory) {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getItem().getId(),
                inventory.getItem().getItemCode(),
                inventory.getItem().getItemName(),
                inventory.getWarehouse().getId(),
                inventory.getWarehouse().getWarehouseCode(),
                inventory.getWarehouse().getWarehouseName(),
                inventory.getQuantity()
        );
    }
}
