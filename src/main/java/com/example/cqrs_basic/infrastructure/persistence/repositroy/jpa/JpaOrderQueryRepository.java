package com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa;

import com.example.cqrs_basic.application.query.dto.OrderSummaryDto;
import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.entity.OrderJpaEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrderQueryRepository extends JpaRepository<OrderJpaEntity, Long> {
    @Query("SELECT new com.example.cqrs_basic.application.query.dto.OrderSummaryDto(o.id, c.name, o.totalAmount, o.orderDate, o.status, SIZE(o.orderItems)) " +
            "FROM OrderJpaEntity o JOIN o.customer c WHERE c.id = :customerId")
    List<OrderSummaryDto> findOrderSummariesByCustomerId(Long customerId);

    @Query("SELECT new com.example.cqrs_basic.application.query.dto.OrderSummaryDto(o.id, c.name, o.totalAmount, o.orderDate, o.status, SIZE(o.orderItems)) " +
            "FROM OrderJpaEntity o JOIN o.customer c WHERE o.id = :orderId")
    Optional<OrderSummaryDto> findOrderSummaryById(Long orderId);
}
