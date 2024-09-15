package com.example.cqrs_basic.presentation.api;

import com.example.cqrs_basic.application.query.dto.CustomerOrdersDto;
import com.example.cqrs_basic.application.query.dto.OrderSummaryDto;
import com.example.cqrs_basic.application.query.service.OrderQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderQueryController {
    private final OrderQueryService orderQueryService;

    public OrderQueryController(OrderQueryService orderQueryService) {
        this.orderQueryService = orderQueryService;
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<CustomerOrdersDto> getCustomerOrders(@PathVariable Long customerId) {
        CustomerOrdersDto customerOrders = orderQueryService.getCustomerOrders(customerId);
        return ResponseEntity.ok(customerOrders);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderSummaryDto> getOrderSummary(@PathVariable Long orderId) {
        OrderSummaryDto orderSummary = orderQueryService.getOrderSummary(orderId);
        return ResponseEntity.ok(orderSummary);
    }
}