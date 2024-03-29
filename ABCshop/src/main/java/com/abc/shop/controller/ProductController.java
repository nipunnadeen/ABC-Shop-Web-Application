package com.abc.shop.controller;

import com.abc.shop.model.Product;
import com.abc.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(defaultValue="0", name = "page") Integer pageSize ,
            @RequestParam(defaultValue="5", name = "number") Integer numberOfItems,
            @RequestParam(defaultValue="id", name = "field") String sortBy) {
        return productService.getAllProducts(pageSize, numberOfItems, sortBy);
    }

//    @GetMapping("/product")
//    public ResponseEntity<List<Product>> getAllProducts(@RequestParam Optional<Integer> page,
//                                                        @RequestParam Optional<Integer> number,
//                                                        @RequestParam Optional<String> sortBy) {
//        return productService.getAllProducts(page, number, sortBy);
//    }

//    @GetMapping("/product")
//    public ResponseEntity<List<Product>> getAllProducts() {
//        return productService.getAllProducts();
//    }

    @PostMapping("/product/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductDetailById(@PathVariable("id") Long productId){
        return productService.getProduct(productId);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProductById(@RequestBody Product productDetails,
                                                 @PathVariable("id") Long productId){
        return productService.updateProduct(productDetails, productId);
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity removeProductById(@PathVariable("id") Long productId){
        return productService.deleteProduct(productId);
    }
}
