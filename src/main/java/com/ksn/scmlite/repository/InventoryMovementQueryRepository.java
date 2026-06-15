package com.ksn.scmlite.repository;

import com.ksn.scmlite.dto.InventoryMovementResponse;
import com.ksn.scmlite.dto.InventoryMovementSearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ksn.scmlite.entity.QInventory.inventory;
import static com.ksn.scmlite.entity.QInventoryMovement.inventoryMovement;
import static com.ksn.scmlite.entity.QItem.item;
import static com.ksn.scmlite.entity.QWarehouse.warehouse;

@Repository
@RequiredArgsConstructor
public class InventoryMovementQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<InventoryMovementResponse> search(InventoryMovementSearchRequest request){
        BooleanBuilder builder = new BooleanBuilder();

        if (request.itemCode() != null && !request.itemCode().isBlank()){
            builder.and(item.itemCode.contains(request.itemCode()));
        }

        if (request.movementType() != null){
            builder.and(inventoryMovement.movementType.eq(request.movementType()));
        }

        return queryFactory
                .select(Projections.constructor(
                        InventoryMovementResponse.class,
                        inventoryMovement.id,
                        item.id,
                        item.itemCode,
                        item.itemName,
                        warehouse.id,
                        warehouse.warehouseCode,
                        warehouse.warehouseName,
                        inventoryMovement.movementType,
                        inventoryMovement.quantity,
                        inventoryMovement.movementDate
                ))
                .from(inventoryMovement)
                .join(inventoryMovement.item, item)
                .join(inventoryMovement.warehouse, warehouse)
                .where(builder)
                .orderBy(inventoryMovement.movementDate.desc())
                .fetch();
    }
}
