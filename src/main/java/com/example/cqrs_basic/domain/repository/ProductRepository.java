package com.example.cqrs_basic.domain.repository;

import com.example.cqrs_basic.domain.model.Product;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(Long id);
}