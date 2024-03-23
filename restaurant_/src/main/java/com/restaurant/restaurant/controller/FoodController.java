package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.model.Category;
import com.restaurant.restaurant.model.Food;
import com.restaurant.restaurant.model.User;
import com.restaurant.restaurant.service.FoodService;
import com.restaurant.restaurant.service.RestaurantService;
import com.restaurant.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;
    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String name, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.searchFood(name);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<Food>> getRestaurantsFood(@RequestParam boolean vegetarian,
                                                         @RequestParam boolean nonVegetarian,
                                                         @RequestParam boolean seasonal,
                                                         @RequestParam (required = false) String food_category_name,
                                                         @PathVariable Long restaurantId,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Food> foods = foodService.getRestaurantsFood(restaurantId, vegetarian, nonVegetarian, seasonal, food_category_name);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
