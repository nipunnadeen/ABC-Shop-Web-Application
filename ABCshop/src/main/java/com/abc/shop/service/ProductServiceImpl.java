package com.abc.shop.service;

import com.abc.shop.model.Product;
import com.abc.shop.model.User;
import com.abc.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
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
//            for(int i=0; i<productsData.size(); i++){
//                Product specificProduct  = productsData.get(i);
//                if(specificProduct.getDeletedAt() == null){
//                    productsData.remove(specificProduct);
//                }
//            }
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

        User user = new User();
        user.setId(91);
//        user.getProducts().add(product);
        product.setCreatedBy(user);

        try {
            if ((!product.getProductName().isEmpty() && product.getProductPrice() > 0 &&
                    !product.getProductDescription().isEmpty()) && (product.getProductName() != null &&
                    product.getProductDescription() != null)) {
//                if (productRepository.findUserByName(product.getEmail()) == null) {

                    productsData = new Product(product.getProductName(), product.getProductDescription(),
                            product.getProductQuantity(), product.getProductPrice(),
                            product.getCreatedBy(),
                            91, 91, 1);
//                    product.setProductName(product.getProductName());
//                    product.setProductDescription(product.getProductDescription());
//                    product.setProductPrice(product.getProductPrice());
//                    product.setCreatedBy(8);
//                    product.setUpdatedBy(8);
//                    product.setDeletedBy(8);
//                    product.setPromotionId(product.getPromotionId());
//                    productsData = productRepository.save(productsData);
                    response = new ResponseEntity<>(productRepository.save(productsData),
                            HttpStatus.OK);
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

        ResponseEntity<Product> response;
        try {
            if (productId != null && productId > 0) {
                Product productData = productRepository.findProductById(productId);
                if (productData != null) {
                    response = new ResponseEntity<>(productData, HttpStatus.OK);
                } else {
                    response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product, Long productId) {
        ResponseEntity<Product> response;
        Product productsData;

        try {
            if((!product.getProductName().isEmpty() && product.getProductPrice() > 0 &&
                    !product.getProductDescription().isEmpty()) && (product.getProductName() != null &&
                    product.getProductDescription() != null)) {
                productsData = productRepository.findProductById(productId);
                if (productsData != null) {

                    productsData.setProductName(product.getProductName());
                    productsData.setProductDescription(product.getProductDescription());
                    productsData.setProductQuantity(product.getProductQuantity());
                    productsData.setProductPrice(product.getProductPrice());
                    productsData.setUpdatedAt(new Date());
                    productsData.setUpdatedBy(91);
                    productsData.setPromotionId(product.getPromotionId());
//                    if(products == null ) {
                        Product userDetail = productRepository.save(productsData);
                        response = new ResponseEntity<>(userDetail, HttpStatus.OK);
//                    } else {
//                        if(products.getId() == productId ) {
//                            Product productDetail = productRepository.save(productsData);
//                            response = new ResponseEntity<>(productDetail, HttpStatus.OK);
//                        } else {
//                            response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//                        }
//                    }
                } else {
                    response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            } else {
                response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e){
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity deleteProduct(Long productId) {

        ResponseEntity response;
        Product productsData;
        try{
            if(productId != null && productId > 0) {
                productsData = productRepository.findProductById(productId);
                if (productsData != null) {
                    productsData.setDeletedAt(new Date());
//                    productRepository.deleteById(productId);
                    response = new ResponseEntity<>(HttpStatus.OK);
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
