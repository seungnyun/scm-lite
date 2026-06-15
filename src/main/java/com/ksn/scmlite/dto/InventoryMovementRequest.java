package com.ksn.scmlite.dto;

public record InventoryMovementRequest(Long itemId, Long warehouseId, Integer quantity) {
}
