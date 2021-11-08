package com.abc.shop.repository;

import com.abc.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findProductById(Long id);
    Product findProductById(Product id);
//    Product findProductByProductNameLike(String name);
}
