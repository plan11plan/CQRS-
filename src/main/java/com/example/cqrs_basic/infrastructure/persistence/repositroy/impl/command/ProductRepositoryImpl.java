package com.example.cqrs_basic.infrastructure.persistence.repositroy.impl.command;

import com.example.cqrs_basic.domain.model.Product;
import com.example.cqrs_basic.domain.repository.ProductRepository;
import com.example.cqrs_basic.infrastructure.persistence.mapper.ProductMapper;
import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.JpaProductRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private final JpaProductRepository jpaRepository;
    private final ProductMapper productMapper;

    public Optional<Product> findById(Long id) {
        return jpaRepository.findById(id).map(productMapper::toDomain);
    }
}
