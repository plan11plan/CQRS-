package com.example.cqrs_basic.infrastructure.persistence.mapper;

import com.example.cqrs_basic.domain.model.Customer;
import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.entity.CustomerJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerJpaEntity toEntity(Customer customer) {
        if (customer == null) {
            return null;
        }

        CustomerJpaEntity entity = new CustomerJpaEntity();
        entity.setId(customer.getId());
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        return entity;
    }

    public Customer toDomain(CustomerJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        Customer customer = new Customer();
        customer.setId(entity.getId());
        customer.setName(entity.getName());
        customer.setEmail(entity.getEmail());
        return customer;
    }
}