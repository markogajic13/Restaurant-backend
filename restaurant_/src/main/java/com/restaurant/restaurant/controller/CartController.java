package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.model.Cart;
import com.restaurant.restaurant.model.CartItem;
import com.restaurant.restaurant.model.User;
import com.restaurant.restaurant.request.AddCartItemRequest;
import com.restaurant.restaurant.request.UpdateCartItemRequest;
import com.restaurant.restaurant.service.CartService;
import com.restaurant.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @PutMapping("/cart/add-item")
    public ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest request, @RequestHeader ("Authorization")String jwt) throws Exception{
        CartItem cartItem = cartService.addItemToCart(request,jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
    @PutMapping("/cart/update-item")
    public ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody UpdateCartItemRequest request, @RequestHeader ("Authorization")String jwt) throws Exception{
        CartItem cartItem = cartService.updateCartItemQuantity(request.getCartItemId(), request.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);
    }
    @DeleteMapping("/cart/delete-item/{id}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable Long id, @RequestHeader ("Authorization")String jwt) throws Exception{
        Cart cart = cartService.removeItemFromCart(id, jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    // removeCart
    @PutMapping("/cart/clear-cart")
    public ResponseEntity<Cart> clearCart(@RequestHeader ("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.clearCart(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
    @GetMapping("/cart")
    public ResponseEntity<Cart> findCartByUserId(@RequestHeader ("Authorization")String jwt) throws Exception{
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = cartService.findCartByUserId(user.getId());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

}
