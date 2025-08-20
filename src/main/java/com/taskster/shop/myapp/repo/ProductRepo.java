package com.taskster.shop.myapp.repo;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.taskster.shop.myapp.model.ProductEntity;

public interface ProductRepo extends MongoRepository<ProductEntity, String>{
    Optional<ProductEntity> findByProductId(String productId);
}
