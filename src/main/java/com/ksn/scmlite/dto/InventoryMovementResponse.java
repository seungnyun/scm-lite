package com.ksn.scmlite.dto;

import com.ksn.scmlite.entity.MovementType;

import java.time.LocalDateTime;

public record InventoryMovementResponse (
        Long movementId,
        Long itemId,
        String itemCode,
        String itemName,
        Long warehouseId,
        String warehouseCode,
        String warehouseName,
        MovementType movementType,
        Integer quantity,
        LocalDateTime movementDate
) {
}
