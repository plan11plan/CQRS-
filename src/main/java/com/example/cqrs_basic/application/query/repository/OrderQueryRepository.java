package com.example.cqrs_basic.application.query.repository;

import com.example.cqrs_basic.application.query.dto.OrderSummaryDto;
import java.util.List;
import java.util.Optional;

public interface OrderQueryRepository {
    List<OrderSummaryDto> findOrderSummariesByCustomerId(Long customerId);
    Optional<OrderSummaryDto> findOrderSummaryById(Long orderId);
}