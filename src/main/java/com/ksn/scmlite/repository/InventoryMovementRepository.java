package com.ksn.scmlite.repository;

import com.ksn.scmlite.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, Long> {
}
