package com.taskster.shop.myapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.taskster.shop.myapp.model.ProductEntity;
import com.taskster.shop.myapp.repo.ProductRepo;

@Service
public class ProductService {
    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }

    public ProductEntity getProductById(String productId) {
        return productRepo.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with productId: " + productId));
    }

    public ProductEntity createProduct(ProductEntity product) {
        if(productRepo.findByProductId(product.getProductId()).isPresent()) {
           try{
               throw new RuntimeException("Product with productId already exists: " + product.getProductId());
           } catch (Exception e) {
               throw new RuntimeException("Error creating product with productId: " + product.getProductId(), e);
           }
        }
        return productRepo.save(product);
    }

    public List<ProductEntity> createProducts(List<ProductEntity> products) {
        for (ProductEntity product : products) {
            if (productRepo.findByProductId(product.getProductId()).isPresent()) {
                try {
                    productRepo.save(product);
                } catch (Exception e) {
                    throw new RuntimeException("Error saving product with productId: " + product.getProductId(), e);
                }
            }
        }
        return productRepo.saveAll(products);
    }
   
}
