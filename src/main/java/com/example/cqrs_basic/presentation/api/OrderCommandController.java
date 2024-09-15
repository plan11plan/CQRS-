package com.example.cqrs_basic.presentation.api;

import com.example.cqrs_basic.application.command.dto.CreateOrderCommand;
import com.example.cqrs_basic.application.command.service.OrderCommandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderCommandController {
    private final OrderCommandService orderCommandService;

    public OrderCommandController(OrderCommandService orderCommandService) {
        this.orderCommandService = orderCommandService;
    }

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody CreateOrderCommand command) {
        Long orderId = orderCommandService.createOrder(command);
        return ResponseEntity.ok(orderId);
    }
}