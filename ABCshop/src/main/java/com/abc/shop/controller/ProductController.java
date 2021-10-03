package com.abc.shop.controller;

import com.abc.shop.model.Product;
import com.abc.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/Product/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/Product/{id}")
    public ResponseEntity<Product> getProductDetail(@RequestParam Long productId){
        return productService.getProduct(productId);
    }

    @PutMapping("/Product/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product productDetails,
                                              @RequestParam Long productId){
        return productService.updateProduct(productDetails, productId);
    }

    @DeleteMapping("/Product/{id}")
    public ResponseEntity removeProduct(@RequestParam Long productId){
        return productService.deleteProduct(productId);
    }
}
