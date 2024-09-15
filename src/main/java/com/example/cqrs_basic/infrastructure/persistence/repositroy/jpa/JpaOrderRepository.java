package com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa;


import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaOrderRepository extends JpaRepository<OrderJpaEntity,Long> {

}