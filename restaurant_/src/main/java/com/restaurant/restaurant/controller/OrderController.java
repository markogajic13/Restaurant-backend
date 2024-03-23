package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.model.Order;
import com.restaurant.restaurant.model.User;
import com.restaurant.restaurant.request.OrderRequest;
import com.restaurant.restaurant.service.OrderService;
import com.restaurant.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @PostMapping("/customer/create-order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderRequest request, @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order order = orderService.createOrder(request,user);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
    @GetMapping("/customer/orders-history")
    public ResponseEntity <List<Order>> customerOrderHistory(@RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> ordersHistory = orderService.getUserOrder(user.getId());
        return new ResponseEntity<>(ordersHistory, HttpStatus.OK);
    }
}
