package com.taskster.shop.myapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskster.shop.myapp.model.ProductEntity;
import com.taskster.shop.myapp.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    
    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductEntity> getProductByProductId(@PathVariable String productId) {
        try {
            ProductEntity product = productService.getProductById(productId);
            return ResponseEntity.ok(product);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // only for testing purpose
    @PostMapping("/add-singleproduct")
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
       try {
           ProductEntity createdProduct = productService.createProduct(product);
           return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
       } catch (RuntimeException e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
       }
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ProductEntity>> createProducts(@RequestBody List<ProductEntity> products) {
        try {
            List<ProductEntity> createdProducts = productService.createProducts(products);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProducts);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}