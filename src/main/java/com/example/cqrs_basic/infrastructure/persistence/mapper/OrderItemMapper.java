package com.example.cqrs_basic.infrastructure.persistence.mapper;

import com.example.cqrs_basic.domain.model.OrderItem;
import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.entity.OrderItemJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderItemMapper {

    private final ProductMapper productMapper;

    public OrderItemMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public OrderItemJpaEntity toEntity(OrderItem orderItem) {
        if (orderItem == null) {
            return null;
        }

        OrderItemJpaEntity entity = new OrderItemJpaEntity();
        entity.setId(orderItem.getId());
        entity.setProduct(productMapper.toEntity(orderItem.getProduct()));
        entity.setQuantity(orderItem.getQuantity());
        entity.setPrice(orderItem.getPrice());
        return entity;
    }

    public OrderItem toDomain(OrderItemJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setId(entity.getId());
        orderItem.setProduct(productMapper.toDomain(entity.getProduct()));
        orderItem.setQuantity(entity.getQuantity());
        orderItem.setPrice(entity.getPrice());
        return orderItem;
    }
}