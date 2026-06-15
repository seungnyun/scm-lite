package com.ksn.scmlite.service;

import com.ksn.scmlite.dto.InventoryMovementRequest;
import com.ksn.scmlite.entity.*;
import com.ksn.scmlite.repository.InventoryMovementRepository;
import com.ksn.scmlite.repository.InventoryRepository;
import com.ksn.scmlite.repository.ItemRepository;
import com.ksn.scmlite.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryMovementService {

    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryMovementRepository inventoryMovementRepository;

    @Transactional
    public void inbound(InventoryMovementRequest request){
        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(() -> new RuntimeException("품목을 찾을 수 없습니다."));

        Warehouse warehouse = warehouseRepository.findById(request.warehouseId())
                .orElseThrow(() -> new RuntimeException("창고를 찾을 수 없습니다."));

        Inventory inventory = inventoryRepository.findByItemIdAndWarehouseId(request.itemId(), request.warehouseId())
                .orElseThrow(() -> new RuntimeException("재고를 찾을 수 없습니다."));

        InventoryMovement movement = new InventoryMovement(
                item,
                warehouse,
                MovementType.INBOUND,
                request.quantity()
        );

        inventoryMovementRepository.save(movement);

        inventory.setQuantity(inventory.getQuantity() + request.quantity());
    }

    @Transactional
    public void outbound(InventoryMovementRequest request){
        Item item = itemRepository.findById(request.itemId())
                .orElseThrow(() -> new RuntimeException("품목을 찾을 수 없습니다."));

        Warehouse warehouse = warehouseRepository.findById(request.warehouseId())
                .orElseThrow(() -> new RuntimeException("창고를 찾을 수 없습니다."));

        Inventory inventory = inventoryRepository.findByItemIdAndWarehouseId(request.itemId(), request.warehouseId())
                .orElseThrow(() -> new RuntimeException("재고를 찾을 수 없습니다."));

        if (inventory.getQuantity() < request.quantity()){
            throw new RuntimeException("재고가 부족합니다");
        }

        InventoryMovement movement = new InventoryMovement(
                item,
                warehouse,
                MovementType.OUTBOUND,
                request.quantity()
        );

        inventoryMovementRepository.save(movement);

        inventory.setQuantity(inventory.getQuantity() - request.quantity());
    }

}
