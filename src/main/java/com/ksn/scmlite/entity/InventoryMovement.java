package com.ksn.scmlite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    @Enumerated(EnumType.STRING)
    private MovementType movementType;

    private Integer quantity;

    private LocalDateTime movementDate;

    public InventoryMovement(Item item, Warehouse warehouse, MovementType movementType, Integer quantity) {
        this.item = item;
        this.warehouse = warehouse;
        this.movementType = movementType;
        this.quantity = quantity;
        this.movementDate = LocalDateTime.now();
    }
}
