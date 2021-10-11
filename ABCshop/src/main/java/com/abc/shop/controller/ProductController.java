package com.abc.shop.controller;

import com.abc.shop.model.Product;
import com.abc.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/product/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/product/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProductDetail(@PathVariable("id") Long productId){
        return productService.getProduct(productId);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product productDetails,
                                                 @PathVariable("id") Long productId){
        return productService.updateProduct(productDetails, productId);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity removeProduct(@PathVariable("id") Long productId){
        return productService.deleteProduct(productId);
    }
}
