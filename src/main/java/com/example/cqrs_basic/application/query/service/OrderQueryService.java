package com.example.cqrs_basic.application.query.service;

import com.example.cqrs_basic.application.query.dto.CustomerOrdersDto;
import com.example.cqrs_basic.application.query.dto.OrderSummaryDto;
import com.example.cqrs_basic.application.query.repository.OrderQueryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderQueryService {
    private final OrderQueryRepository orderQueryRepository;


    public CustomerOrdersDto getCustomerOrders(Long customerId) {
        List<OrderSummaryDto> orders = orderQueryRepository.findOrderSummariesByCustomerId(customerId);
        CustomerOrdersDto dto = new CustomerOrdersDto();
        if (!orders.isEmpty()) {
            dto.setCustomerName(orders.get(0).getCustomerName());
            // Assuming you have a way to get customer email
        }
        dto.setOrders(orders);
        return dto;
    }

    public OrderSummaryDto getOrderSummary(Long orderId) {
        return orderQueryRepository.findOrderSummaryById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
}