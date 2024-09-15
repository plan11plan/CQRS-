package com.example.cqrs_basic.infrastructure.persistence.repositroy.query;


import com.example.cqrs_basic.application.query.dto.OrderSummaryDto;
import com.example.cqrs_basic.application.query.repository.OrderQueryRepository;
import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.JpaOrderQueryRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository {
    private final JpaOrderQueryRepository jpaOrderQueryRepository;

    @Override
    public List<OrderSummaryDto> findOrderSummariesByCustomerId(Long customerId) {
        return jpaOrderQueryRepository.findOrderSummariesByCustomerId(customerId);
    }

    @Override
    public Optional<OrderSummaryDto> findOrderSummaryById(Long orderId) {
        return jpaOrderQueryRepository.findOrderSummaryById(orderId);
    }
}
