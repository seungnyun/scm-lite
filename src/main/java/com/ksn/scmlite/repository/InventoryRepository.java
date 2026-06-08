package com.ksn.scmlite.repository;

import com.ksn.scmlite.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
