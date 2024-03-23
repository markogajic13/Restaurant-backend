package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.model.Order;
import com.restaurant.restaurant.model.User;
import com.restaurant.restaurant.service.OrderService;
import com.restaurant.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminOrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/restaurant/orders-history/{id}")
    public ResponseEntity <List<Order>> restaurantOrderHistory(@PathVariable Long id, @RequestParam(required = false) String orderStatus, @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        List<Order> ordersHistory = orderService.getRestaurantOrder(id, orderStatus);
        return new ResponseEntity<>(ordersHistory, HttpStatus.OK);
    }
    @PutMapping("/restaurant/update-order/{id}/{orderStatus}")
    public ResponseEntity <Order> restaurantUpdateOrder(@PathVariable Long id, @PathVariable String orderStatus, @RequestHeader("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Order ordersHistory = orderService.updateOrder(id, orderStatus);
        return new ResponseEntity<>(ordersHistory, HttpStatus.OK);
    }
}
