package com.ksn.scmlite.dto;

import com.ksn.scmlite.entity.MovementType;

public record InventoryMovementSearchRequest (
        String itemCode,
        MovementType movementType
) {
}
