package com.restaurant.restaurant.service;

import com.restaurant.restaurant.model.Category;
import com.restaurant.restaurant.model.Food;
import com.restaurant.restaurant.model.Restaurant;
import com.restaurant.restaurant.request.CreateFoodRequest;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodRequest request, Category category, Restaurant restaurant);
    public void deleteFood(Long foodId) throws Exception;
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegetarian, boolean isNonVegetarian, boolean isSeasonal, String foodCategory);
    public List<Food> searchFood(String keyword);
    public Food findFoodById(Long foodId) throws Exception;
    public Food updateFoodAvailabilityStatus(Long foodId) throws Exception;
}
