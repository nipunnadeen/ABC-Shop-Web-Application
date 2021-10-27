package com.abc.shop.service;

import com.abc.shop.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ResponseEntity<List<Product>> getAllProducts(Optional<Integer> page,
                                                 Optional<String> sortBy);

    ResponseEntity<Product> createProduct(Product product);

    ResponseEntity<Product> getProduct(Long productId);

    ResponseEntity<Product> updateProduct(Product product, Long productId);

    ResponseEntity deleteProduct(Long productId);
}
