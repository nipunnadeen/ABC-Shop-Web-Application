package com.abc.shop.service;

import com.abc.shop.model.Order;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<List<Order>> getAllOrders();

    ResponseEntity<Order> createOrder(Order order, Long productId);

    ResponseEntity<Order> getOrder(Long orderId);

    ResponseEntity<Order> updateOrder(Order order, Long orderId);

    ResponseEntity deleteOrder(Long orderId);
}
