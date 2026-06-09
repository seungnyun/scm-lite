package com.ksn.scmlite.repository;

import com.ksn.scmlite.dto.InventoryResponse;
import com.ksn.scmlite.dto.InventorySearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.ksn.scmlite.entity.QInventory.inventory;
import static com.ksn.scmlite.entity.QItem.item;
import static com.ksn.scmlite.entity.QWarehouse.warehouse;

@Repository
@RequiredArgsConstructor
public class InventoryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<InventoryResponse> search(InventorySearchRequest request){
        BooleanBuilder builder = new BooleanBuilder();

        if (request.itemCode() != null && !request.itemCode().isBlank()){
            builder.and(item.itemCode.startsWith(request.itemCode()));
        }

        if (request.warehouseCode() != null && !request.warehouseCode().isBlank()){
            builder.and(warehouse.warehouseCode.startsWith(request.warehouseCode()));
        }

        return queryFactory
                .select(Projections.constructor(
                        InventoryResponse.class,
                        inventory.id,
                        item.id,
                        item.itemCode,
                        item.itemName,
                        warehouse.id,
                        warehouse.warehouseCode,
                        warehouse.warehouseName,
                        inventory.quantity))
                .from(inventory)
                .join(inventory.item, item)
                .join(inventory.warehouse, warehouse)
                .where(builder)
                .fetch();
    }
}
