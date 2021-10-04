package com.abc.shop.service;

import com.abc.shop.model.Product;
import com.abc.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<Product>> getAllProducts() {
        ResponseEntity<List<Product>> response;
        List<Product> productsData;
        try {
            productsData = productRepository.findAll();
            response = new ResponseEntity<>(productsData, HttpStatus.OK);
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        ResponseEntity<Product> response;
        Product productsData;

        try {
            if ((!product.getProductName().isEmpty() && product.getProductPrice() > 0 &&
                    !product.getProductDescription().isEmpty()) && (product.getProductName() != null &&
                    product.getProductDescription() != null)) {
//                if (productRepository.findUserByName(product.getEmail()) == null) {
                    product.setProductName(product.getProductName());
                    product.setProductDescription(product.getProductDescription());
                    product.setProductPrice(product.getProductPrice());
                    product.setCreatedBy(8);
                    product.setUpdatedBy(8);
//                    product.setDeletedBy(8);
//                    product.setPromotionId();
                    productsData = productRepository.save(product);
                    response = new ResponseEntity<>(productsData, HttpStatus.OK);
//                } else {
//                    response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
//                }
            } else {
                response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
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

        ResponseEntity response;
        Product productsData;
        try{
            if(productId != null && productId > 0) {
                productsData = productRepository.findProductByid(productId);
                if (productsData != null) {
                    productRepository.deleteById(productId);
                    response = new ResponseEntity<>(productRepository, HttpStatus.OK);
                } else {
                    response = new ResponseEntity<>(("Product not exist with id :" + productId),
                            HttpStatus.NOT_FOUND);
                }
            } else {
                response = new ResponseEntity<>(("Product Id is not acceptable"),
                        HttpStatus.NOT_ACCEPTABLE);
            }

        } catch(Exception e) {
            response = new ResponseEntity<>("Something went wrong",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }
}
