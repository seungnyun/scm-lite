package com.ksn.scmlite.dto;

public record InventoryShortageResponse (
        Long inventoryId,
        Long itemId,
        String itemCode,
        String itemName,
        Long warehouseId,
        String warehouseCode,
        String warehouseName,
        Integer quantity,
        Integer safetyStock,
        Integer shortageQuantity
) {
}
