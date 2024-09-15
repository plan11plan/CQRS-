package com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa;


import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.entity.CustomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<CustomerJpaEntity,Long>{

}