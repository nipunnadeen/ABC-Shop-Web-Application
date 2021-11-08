package com.abc.shop.service;

import com.abc.shop.exception.InvalidInputException;
import com.abc.shop.exception.NoSuchElementFoundException;
import com.abc.shop.model.Product;
import com.abc.shop.model.Promotion;
import com.abc.shop.model.User;
import com.abc.shop.repository.ProductRepository;
import com.abc.shop.utill.CommonUtill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<Product>> getAllProducts(Integer page,
                                                        Integer number,
                                                        String sortBy) {

        Page<Product> productsData = productRepository.findAll(PageRequest.of(page, number,
                    Sort.Direction.ASC, sortBy));

//        Page<Product> productsData = productRepository.findAll(PageRequest.of(page, number,
//                Sort.by(sortBy).ascending().and(Sort.by("productQuantity")).
//                        and(Sort.by("productPrice"))));

            return new ResponseEntity(productsData, HttpStatus.OK);
    }

// TODO Do not delete commented codes

//    @Override
//    public ResponseEntity<List<Product>> getAllProducts(Optional<Integer> page,
//                                                        Optional<Integer> number,
//                                                        Optional<String> sortBy) {
//        ResponseEntity<List<Product>> response;
//        Page<Product> productsData;
//        try {
//            productsData = productRepository.findAll(
//                    PageRequest.of(
//                            page.orElse(0), number.orElse(5), Sort.Direction.ASC,
//                            sortBy.orElse("id")
//                    )
//            );
//
////            Pageable sortedByPriceDescNameAsc =
////                    PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));
//
//            response = new ResponseEntity(productsData, HttpStatus.OK);
//        } catch (Exception e) {
//            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return response;
//    }


//    @Override
//    public ResponseEntity<List<Product>> getAllProducts1(Optional<Integer> page,
//                                                        Optional<Integer> number,
//                                                        String sortBy) {
//        ResponseEntity<List<Product>> response;
//        List<Product> productsData;
//        try {
//            productsData = productRepository.findAll(Sort.by(Sort.Direction.ASC, sortBy));
//
//            response = new ResponseEntity(productsData, HttpStatus.OK);
//        } catch (Exception e) {
//            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return response;
//    }


//    @Override
//    public ResponseEntity<List<Product>> getAllProductsByPagination(Integer offSet,
//                                                        Integer pageSize) {
//        Page<Product> productsData;
//            productsData = productRepository.findAll(PageRequest.of(offSet, pageSize));
//
//        return new ResponseEntity(productsData, HttpStatus.OK);
//    }

    @Override
    public ResponseEntity<Product> createProduct(Product product) {
        ResponseEntity<Product> response;
        Product productsData;
        long userId = CommonUtill.userId;

        try {
            if ((!product.getProductName().isEmpty() && product.getProductPrice() > 0 &&
                    !product.getProductDescription().isEmpty()) && (product.getProductName() != null &&
                    product.getProductDescription() != null)) {
                User user = new User();

                user.setId(userId);
                product.setCreatedBy(user);

                Promotion promotion = new Promotion();
                promotion.setId(product.getProductPromoId());
                product.setPromotionId(promotion);
                productsData = new Product(product.getProductName(), product.getProductDescription(),
                        product.getProductQuantity(), product.getProductPrice(),
                        product.getCreatedBy(), product.getCreatedBy(), userId,
                        product.getPromotionId());
                response = new ResponseEntity<>(productRepository.save(productsData),
                        HttpStatus.OK);

            } else {
                response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    /**
     * Returns the single product details according to given product id
     *
     * @param productId - Id of the product
     * @return - Single product data
     */
    @Override
    public ResponseEntity<Product> getProduct(Long productId) {
        if (productId == null || productId < 0) {
            throw new InvalidInputException("Product Id is not acceptable");
        }
        Product productData = productRepository.findProductById(productId);
        if (productData == null) {
            throw new NoSuchElementFoundException("Product with ID " +
                    productId + " does not exist.");
        }
        return new ResponseEntity<>(productData, HttpStatus.OK);
    }

    /**
     * Update the single product details according to given product id and product data
     *
     * @param product   - product details
     * @param productId - Id of the product
     * @return - Updated the product data successfully
     */
    @Override
    public ResponseEntity<Product> updateProduct(Product product, Long productId) {
        Product productsData;
        long userId = CommonUtill.userId;

        if ((product.getProductName().isEmpty() && product.getProductPrice() < 0 &&
                product.getProductDescription().isEmpty()) && (product.getProductName() == null &&
                product.getProductDescription() == null)) {
            throw new InvalidInputException("New product details are not acceptable");
        }
        if (productId == null || productId < 0) {
            throw new InvalidInputException("Product Id is not acceptable");
        }
        productsData = productRepository.findProductById(productId);
        User user = new User();
        user.setId(userId);
        product.setUpdatedBy(user);

        if (productsData == null) {
            throw new NoSuchElementFoundException("Product with ID " +
                    productId + " does not exist.");
        }

        productsData.setProductName(product.getProductName());
        productsData.setProductDescription(product.getProductDescription());
        productsData.setProductQuantity(product.getProductQuantity());
        productsData.setProductPrice(product.getProductPrice());
        productsData.setUpdatedAt(new Date());
        productsData.setUpdatedBy(product.getUpdatedBy());
        productsData.setPromotionId(product.getPromotionId());
        Product userDetail = productRepository.save(productsData);
        return new ResponseEntity<>(userDetail, HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteProduct(Long productId) {

        ResponseEntity response;
        Product productsData;
        try {
            if (productId != null && productId > 0) {
                productsData = productRepository.findProductById(productId);
                if (productsData != null) {
                    productsData.setDeletedAt(new Date());
                    productRepository.save(productsData);
                    response = new ResponseEntity<>(HttpStatus.OK);
                } else {
                    response = new ResponseEntity<>(("Product not exist with id :" + productId),
                            HttpStatus.NOT_FOUND);
                }
            } else {
                response = new ResponseEntity<>(("Product Id is not acceptable"),
                        HttpStatus.NOT_ACCEPTABLE);
            }

        } catch (Exception e) {
            response = new ResponseEntity<>("Something went wrong",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }


//    @Override
//    public ResponseEntity<List<Product>> getAllProducts(Optional<Integer> page,
//                                                        Optional<Integer> number,
//                                                        Optional<String> sortBy) {
//        ResponseEntity<List<Product>> response;
//        Page<Product> productsData;
//        try {
//            productsData = productRepository.findAll(
//                    PageRequest.of(
//                            page.orElse(0), number.orElse(10), Sort.Direction.ASC,
//                            sortBy.orElse("id")
//                    )
//            );
//
////            Pageable sortedByPriceDescNameAsc =
////                    PageRequest.of(0, 5, Sort.by("price").descending().and(Sort.by("name")));
//
//            response = new ResponseEntity(productsData, HttpStatus.OK);
//        } catch (Exception e) {
//            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return response;
//    }
//
//    @Override
//    public ResponseEntity<Product> createProduct(Product product) {
//        ResponseEntity<Product> response;
//        Product productsData;
//        long userId = CommonUtill.userId;
//
//        try {
//            if ((!product.getProductName().isEmpty() && product.getProductPrice() > 0 &&
//                    !product.getProductDescription().isEmpty()) && (product.getProductName() != null &&
//                    product.getProductDescription() != null)) {
//                User user = new User();
//
//                user.setId(userId);
//                product.setCreatedBy(user);
//
//                Promotion promotion = new Promotion();
//                promotion.setId(product.getProductPromoId());
//                product.setPromotionId(promotion);
//                productsData = new Product(product.getProductName(), product.getProductDescription(),
//                        product.getProductQuantity(), product.getProductPrice(),
//                        product.getCreatedBy(),
//                        product.getCreatedBy(), userId, product.getPromotionId());
//                response = new ResponseEntity<>(productRepository.save(productsData),
//                        HttpStatus.OK);
//
//            } else {
//                response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//            }
//        } catch (Exception e) {
//            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return response;
//    }
//
//    /**
//     * Returns the single product details according to given product id
//     *
//     * @param productId - Id of the product
//     * @return - Single product data
//     */
//    @Override
//    public ResponseEntity<Product> getProduct(Long productId) {
//
//        if (productId == null || productId < 0) {
//            throw new InvalidInputException("Product Id is not acceptable");
//        }
//        Product productData = productRepository.findProductById(productId);
//        if (productData == null) {
//            throw new NoSuchElementFoundException("Product with ID " +
//                    productId + " does not exist.");
//        }
//        return new ResponseEntity<>(productData, HttpStatus.OK);
//
////        if (productId != null && productId > 0) {
////            Product productData = productRepository.findProductById(productId);
////            if (productData != null) {
////                return new ResponseEntity<>(productData, HttpStatus.OK);
////            } else {
////                throw new NoSuchElementFoundException("Product Id is not valid");
////            }
////        } else {
////            throw new InvalidInputException("Product Id is not acceptable");
////        }
//    }
//
////    @Override
////    public ResponseEntity<Product> getProduct(Long productId) {
////        ResponseEntity<Product> response;
////        try {
////            if (productId != null && productId > 0) {
////                Product productData = productRepository.findProductById(productId);
////                if (productData != null) {
////                    response = new ResponseEntity<>(productData, HttpStatus.OK);
////                } else {
////                    response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
////                }
////            } else {
////                response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
////            }
////        } catch (Exception e) {
////            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
////        }
////        return response;
////    }
//
//    @Override
//    public ResponseEntity<Product> updateProduct(Product product, Long productId) {
//        ResponseEntity<Product> response;
//        Product productsData;
//        long userId = CommonUtill.userId;
//
//        try {
//            if ((!product.getProductName().isEmpty() && product.getProductPrice() > 0 &&
//                    !product.getProductDescription().isEmpty()) && (product.getProductName() != null &&
//                    product.getProductDescription() != null)) {
//                productsData = productRepository.findProductById(productId);
//                User user = new User();
//                user.setId(userId);
//                product.setUpdatedBy(user);
//                if (productsData != null) {
//
//                    productsData.setProductName(product.getProductName());
//                    productsData.setProductDescription(product.getProductDescription());
//                    productsData.setProductQuantity(product.getProductQuantity());
//                    productsData.setProductPrice(product.getProductPrice());
//                    productsData.setUpdatedAt(new Date());
//                    productsData.setUpdatedBy(product.getUpdatedBy());
//                    productsData.setPromotionId(product.getPromotionId());
//                    Product userDetail = productRepository.save(productsData);
//                    response = new ResponseEntity<>(userDetail, HttpStatus.OK);
//                } else {
//                    response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//                }
//            } else {
//                response = new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
//            }
//        } catch (Exception e) {
//            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return response;
//    }
//
//    @Override
//    public ResponseEntity deleteProduct(Long productId) {
//
//        ResponseEntity response;
//        Product productsData;
//        try {
//            if (productId != null && productId > 0) {
//                productsData = productRepository.findProductById(productId);
//                if (productsData != null) {
//                    productsData.setDeletedAt(new Date());
//                    productRepository.save(productsData);
//                    response = new ResponseEntity<>(HttpStatus.OK);
//                } else {
//                    response = new ResponseEntity<>(("Product not exist with id :" + productId),
//                            HttpStatus.NOT_FOUND);
//                }
//            } else {
//                response = new ResponseEntity<>(("Product Id is not acceptable"),
//                        HttpStatus.NOT_ACCEPTABLE);
//            }
//
//        } catch (Exception e) {
//            response = new ResponseEntity<>("Something went wrong",
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return response;
//    }


}
