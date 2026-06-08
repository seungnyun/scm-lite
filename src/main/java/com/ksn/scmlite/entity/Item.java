package com.ksn.scmlite.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name = "item")
@Getter
@Setter
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_code", nullable = false, unique = true, length = 10)
    private String itemCode;

    @Column(name = "item_name", nullable = false, length = 100)
    private String itemName;

    @Column(name = "safety_stock", nullable = false)
    private Integer safetyStock;

    public void update(String itemName, Integer safetyStock){
        this.itemName = itemName;
        this.safetyStock = safetyStock;
    }
}
