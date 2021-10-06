package com.abc.shop.service;

import com.abc.shop.model.Order;
import com.abc.shop.model.Product;
import com.abc.shop.repository.OrderRepository;
import com.abc.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<Order>> getAllOrders() {
        ResponseEntity<List<Order>> response;
        List<Order> ordersData;
        try {
            ordersData = orderRepository.findAll();
            response = new ResponseEntity<>(ordersData, HttpStatus.OK);
        } catch (Exception e) {
            response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseEntity<Order> createOrder(Order order, Long productId) {
        ResponseEntity<Order> response;
        Order ordersData;
        Product productData;

        try {
            if ((order.getOrderQuantity() > 0 &&
                    productId != null)) {
                productData = productRepository.findProductByid(productId);
                if (productData != null) {
                    double orderPrice = productData.getProductPrice()*order.getOrderQuantity();
                    ordersData = new Order(productId, order.getOrderQuantity(), orderPrice,
                            8, 8);
                    response = new ResponseEntity<>(orderRepository.save(ordersData),
                            HttpStatus.OK);
                } else {
                    response = new ResponseEntity<>(HttpStatus.FORBIDDEN);
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
    public ResponseEntity<Order> getOrder(Long orderId) {
        ResponseEntity<Order> response;
        try {
            if (orderId != null && orderId > 0) {
                Order orderData = orderRepository.findOrderById(orderId);
                if (orderData != null) {
                    response = new ResponseEntity<>(orderData, HttpStatus.OK);
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
    public ResponseEntity<Order> updateOrder(Order order, Long orderId) {

        ResponseEntity<Order> response;
        Order ordersData;
        Product productData;

        try {
            if ((order.getOrderQuantity() > 0 &&
                    orderId != null)) {
                ordersData = orderRepository.findOrderById(orderId);
                if (ordersData != null) {
                    productData = productRepository.findProductByid(ordersData.getProductId());
                    double orderPrice = productData.getProductPrice()*order.getOrderQuantity();
                    ordersData.setOrderQuantity(order.getOrderQuantity());
                    ordersData.setOrderPrice(orderPrice);
                    ordersData.setUpdatedAt(new Date());
                    ordersData.setUpdatedBy(8);
                    ordersData.setProductId(order.getProductId());
                    Order userDetail = orderRepository.save(ordersData);
                    response = new ResponseEntity<>(userDetail, HttpStatus.OK);
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
    public ResponseEntity deleteOrder(Long orderId) {

        ResponseEntity response;
        Order ordersData;

        try{
            if(orderId != null) {
                ordersData = orderRepository.findOrderById(orderId);
                if (ordersData != null) {
                    ordersData.setDeletedAt(new Date());
//                    productRepository.deleteById(productId);
                    response = new ResponseEntity<>(HttpStatus.OK);
                } else {
                    response = new ResponseEntity<>(("Product not exist with id :" + orderId),
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
