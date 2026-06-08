package com.ksn.scmlite.dto;

public record InventoryResponse(Long id, Long itemId, String itemCode, String itemName, Long warehouseId, String warehouseCode, String warehouseName, Integer quantity) {
}
