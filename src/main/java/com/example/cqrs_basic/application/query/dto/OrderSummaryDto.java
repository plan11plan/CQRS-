package com.example.cqrs_basic.application.query.dto;

import com.example.cqrs_basic.application.common.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class OrderSummaryDto {
    private Long id;
    private String customerName;
    private BigDecimal totalAmount;
    private LocalDateTime orderDate;
    private OrderStatus status;
    private int itemCount;

    public OrderSummaryDto(Long id, String customerName, BigDecimal totalAmount, LocalDateTime orderDate,
                           OrderStatus status, int itemCount) {
        this.id = id;
        this.customerName = customerName;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
        this.status = status;
        this.itemCount = itemCount;
    }
}