package com.example.cqrs_basic.domain.repository;

import com.example.cqrs_basic.domain.model.Customer;
import java.util.Optional;

public interface CustomerRepository {
    Optional<Customer> findById(Long id);
}