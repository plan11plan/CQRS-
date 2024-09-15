package com.example.cqrs_basic.infrastructure.persistence.repositroy.impl.command;

import com.example.cqrs_basic.domain.model.Order;
import com.example.cqrs_basic.domain.repository.OrderRepository;
import com.example.cqrs_basic.infrastructure.persistence.mapper.OrderMapper;
import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.JpaOrderQueryRepository;
import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.entity.OrderJpaEntity;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {
    private final JpaOrderQueryRepository jpaRepository;
    private final OrderMapper orderMapper;


    @Override
    public Order save(Order order) {
        OrderJpaEntity entity = orderMapper.toEntity(order);
        OrderJpaEntity savedEntity = jpaRepository.save(entity);
        return orderMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return jpaRepository.findById(id).map(orderMapper::toDomain);
    }
}
