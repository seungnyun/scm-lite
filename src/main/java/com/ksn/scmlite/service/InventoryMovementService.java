package com.ksn.scmlite.service;

import com.ksn.scmlite.dto.InventoryMovementRequest;
import com.ksn.scmlite.dto.InventoryMovementResponse;
import com.ksn.scmlite.dto.InventoryMovementSearchRequest;
import com.ksn.scmlite.entity.*;
import com.ksn.scmlite.exception.BusinessException;
import com.ksn.scmlite.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryMovementService {

    private final ItemRepository itemRepository;
    private final WarehouseRepository warehouseRepository;
    private final InventoryRepository inventoryRepository;
    private final InventoryMovementRepository inventoryMovementRepository;

    private final InventoryMovementQueryRepository inventoryMovementQueryRepository;

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
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "재고를 찾을 수 없습니다."));

        Warehouse warehouse = warehouseRepository.findById(request.warehouseId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "재고를 찾을 수 없습니다."));

        Inventory inventory = inventoryRepository.findByItemIdAndWarehouseId(request.itemId(), request.warehouseId())
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "재고를 찾을 수 없습니다."));

        if (inventory.getQuantity() < request.quantity()){
            throw new BusinessException(HttpStatus.BAD_REQUEST, "재고가 부족합니다.");
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

    public List<InventoryMovementResponse> search(InventoryMovementSearchRequest request){
        return  inventoryMovementQueryRepository.search(request);
    }

}
