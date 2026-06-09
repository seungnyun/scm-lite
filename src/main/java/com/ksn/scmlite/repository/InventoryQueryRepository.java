package com.ksn.scmlite.repository;

import com.ksn.scmlite.dto.InventoryResponse;
import com.ksn.scmlite.dto.InventorySearchRequest;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.ksn.scmlite.entity.QInventory.inventory;
import static com.ksn.scmlite.entity.QItem.item;
import static com.ksn.scmlite.entity.QWarehouse.warehouse;

@Repository
@RequiredArgsConstructor
public class InventoryQueryRepository {

    private final JPAQueryFactory queryFactory;

    public Page<InventoryResponse> search(InventorySearchRequest request, Pageable pageable){
        BooleanBuilder builder = new BooleanBuilder();

        if (request.itemCode() != null && !request.itemCode().isBlank()){
            builder.and(item.itemCode.startsWith(request.itemCode()));
        }

        if (request.warehouseCode() != null && !request.warehouseCode().isBlank()){
            builder.and(warehouse.warehouseCode.startsWith(request.warehouseCode()));
        }

        JPAQuery<InventoryResponse> query = queryFactory
                .select(Projections.constructor(
                        InventoryResponse.class,
                        inventory.id,
                        item.id,
                        item.itemCode,
                        item.itemName,
                        warehouse.id,
                        warehouse.warehouseCode,
                        warehouse.warehouseName,
                        inventory.quantity
                ))
                .from(inventory)
                .join(inventory.item, item)
                .join(inventory.warehouse, warehouse)
                .where(builder);

        for (Sort.Order order : pageable.getSort()) {
            if (order.getProperty().equals("quantity")) {
                query.orderBy(
                        order.isAscending()
                                ? inventory.quantity.asc()
                                : inventory.quantity.desc()
                );
            }
        }

        List<InventoryResponse> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(inventory.count())
                .from(inventory)
                .join(inventory.item, item)
                .join(inventory.warehouse, warehouse)
                .where(builder)
                .fetchOne();

        return new PageImpl<>(
                content,
                pageable,
                total == null ? 0 : total
        );

    }
}
