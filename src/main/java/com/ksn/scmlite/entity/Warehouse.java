package com.ksn.scmlite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "warehouse")
@Getter
@NoArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 10)
    private String warehouseCode;

    @Column(nullable = false, length = 100)
    private String warehouseName;

    public void update(String warehouseName) {
        this.warehouseName = warehouseName;
    }
}
