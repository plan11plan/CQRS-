package com.example.cqrs_basic.application.command.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CreateOrderCommand {
    private Long customerId;
    private List<OrderItemDto> items;

    @Data
    @AllArgsConstructor
    public static class OrderItemDto {
        private Long productId;
        private int quantity;
    }
}