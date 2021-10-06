package com.abc.shop.controller;

import com.abc.shop.model.Order;
import com.abc.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/order/create/{id}")
    public ResponseEntity<Order> createOrder(@RequestBody Order orderDetails,
                                             @PathVariable("id") Long orderId){
        return orderService.createOrder(orderDetails, orderId);
    }

//    @PostMapping("/order/create")
//    public ResponseEntity<Order> createOrder(@RequestBody Order order){
//        return orderService.createOrder(order);
//    }

    @GetMapping("/order/{id}")
    @ResponseBody
    public ResponseEntity<Order> getOrderDetail(@PathVariable("id") Long orderId){
        return orderService.getOrder(orderId);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody Order orderDetails,
                                             @PathVariable("id") Long orderId){
        return orderService.updateOrder(orderDetails, orderId);
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity removeOrder(@PathVariable("id") Long orderId){
        return orderService.deleteOrder(orderId);
    }
}
