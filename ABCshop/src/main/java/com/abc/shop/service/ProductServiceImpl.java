package com.abc.shop.service;

import com.abc.shop.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        return null;
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        return null;
    }

    @Override
    public ResponseEntity<Product> getProduct(Long productId) {
        return null;
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product, Long productId) {
        return null;
    }

    @Override
    public ResponseEntity deleteProduct(Long productId) {
        return null;
    }
}
