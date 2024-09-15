package com.example.cqrs_basic.infrastructure.persistence.mapper;

import com.example.cqrs_basic.domain.model.Product;
import com.example.cqrs_basic.infrastructure.persistence.repositroy.jpa.entity.ProductJpaEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductJpaEntity toEntity(Product product) {
        if (product == null) {
            return null;
        }

        ProductJpaEntity entity = new ProductJpaEntity();
        entity.setId(product.getId());
        entity.setName(product.getName());
        entity.setPrice(product.getPrice());
        return entity;
    }

    public Product toDomain(ProductJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        Product product = new Product();
        product.setId(entity.getId());
        product.setName(entity.getName());
        product.setPrice(entity.getPrice());
        return product;
    }
}