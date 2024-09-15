package com.example.cqrs_basic.infrastructure.persistence.mapper;

import com.example.cqrs_basic.domain.model.Order;
import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.entity.OrderJpaEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderMapper {

    private final CustomerMapper customerMapper;
    private final OrderItemMapper orderItemMapper;



    public OrderJpaEntity toEntity(Order order) {
        if (order == null) {
            return null;
        }

        OrderJpaEntity entity = new OrderJpaEntity();
        entity.setId(order.getId());
        entity.setCustomer(customerMapper.toEntity(order.getCustomer()));
        entity.setTotalAmount(order.getTotalAmount());
        entity.setOrderDate(order.getOrderDate());
        entity.setStatus(order.getStatus());
        entity.setOrderItems(order.getOrderItems().stream()
                .map(orderItemMapper::toEntity)
                .collect(java.util.stream.Collectors.toList()));
        return entity;
    }

    public Order toDomain(OrderJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        Order order = new Order();
        order.setId(entity.getId());
        order.setCustomer(customerMapper.toDomain(entity.getCustomer()));
        order.setTotalAmount(entity.getTotalAmount());
        order.setOrderDate(entity.getOrderDate());
        order.setStatus(entity.getStatus());
        order.setOrderItems(entity.getOrderItems().stream()
                .map(orderItemMapper::toDomain)
                .collect(java.util.stream.Collectors.toList()));
        return order;
    }
}