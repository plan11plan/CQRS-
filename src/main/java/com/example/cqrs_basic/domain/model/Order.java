package com.example.cqrs_basic.domain.model;

import com.example.cqrs_basic.application.common.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order {
    private Long id;
    private Customer customer;
    private List<OrderItem> orderItems = new ArrayList<>();
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private OrderStatus status;

    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
    }

    public void calculateTotalAmount() {
        this.totalAmount = orderItems.stream()
                .map(OrderItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}