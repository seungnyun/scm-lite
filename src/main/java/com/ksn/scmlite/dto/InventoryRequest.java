package com.ksn.scmlite.dto;

public record InventoryRequest(Long itemId, Long warehouseId, Integer quantity) {
}
