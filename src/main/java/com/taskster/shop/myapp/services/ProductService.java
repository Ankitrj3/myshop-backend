package com.taskster.shop.myapp.services;

import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.taskster.shop.myapp.model.ProductEntity;
import com.taskster.shop.myapp.repo.ProductRepo;

@Service
public class ProductService {
    private final ProductRepo productRepo;
    // Logger logger = LoggerFactory.getLogger(ProductService.class);
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<ProductEntity> getAllProducts() {
        return productRepo.findAll();
    }

    public ProductEntity getProductById(String productId) {
        // logger.info("Fetching product with productId: {}", productId);
        ProductEntity product = productRepo.findByProductId(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with productId: " + productId));
        // logger.info("Fetched product: {}", product);
        return product;
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
