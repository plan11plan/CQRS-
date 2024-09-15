package com.example.cqrs_basic.infrastructure.persistence.repositroy.command;

import com.example.cqrs_basic.domain.model.Customer;
import com.example.cqrs_basic.domain.repository.CustomerRepository;
import com.example.cqrs_basic.infrastructure.persistence.mapper.CustomerMapper;
import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.JpaCustomerRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final JpaCustomerRepository jpaRepository;
    private final CustomerMapper customerMapper;


    @Override
    public Optional<Customer> findById(Long id) {
        return jpaRepository.findById(id).map(customerMapper::toDomain);
    }
}
