package com.ksn.scmlite.repository;

import com.ksn.scmlite.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByItemIdAndWarehouseId(Long itemId, Long warehouseId);
}
