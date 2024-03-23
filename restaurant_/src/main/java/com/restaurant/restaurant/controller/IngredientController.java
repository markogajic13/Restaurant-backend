package com.restaurant.restaurant.controller;

import com.restaurant.restaurant.model.IngredientCategory;
import com.restaurant.restaurant.model.IngredientsItem;
import com.restaurant.restaurant.request.IngredientCategoryRequest;
import com.restaurant.restaurant.request.IngredientsItemRequest;
import com.restaurant.restaurant.service.IngredientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/ingredients")
public class IngredientController {
    @Autowired
    private IngredientsService ingredientsService;

    @PostMapping("/create-category")
    public ResponseEntity<IngredientCategory> createIngredientCategory(@RequestBody IngredientCategoryRequest request) throws Exception {
        IngredientCategory ingredientCategory=ingredientsService.createIngredientCategory(request.getName(), request.getRestaurantId());
        return new ResponseEntity<>(ingredientCategory, HttpStatus.CREATED);
    }
    @PostMapping("/create-ingredient-item")
    public ResponseEntity<IngredientsItem> createIngredientItem(@RequestBody IngredientsItemRequest request) throws Exception {
        IngredientsItem ingredientsItem=ingredientsService.createIngredientItem(request.getRestaurantId(), request.getName(), request.getCategoryId());
        return new ResponseEntity<>(ingredientsItem, HttpStatus.CREATED);
    }
    @PutMapping("/{id}/stock")
    public ResponseEntity<IngredientsItem> updateIngredientsStock(@PathVariable Long id) throws Exception {
        IngredientsItem ingredientsItem=ingredientsService.updateIngredientsStock(id);
        return new ResponseEntity<>(ingredientsItem, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<IngredientsItem>> findRestaurantsIngredients(@PathVariable Long id) throws Exception {
        List<IngredientsItem> ingredientsItems=ingredientsService.findRestaurantsIngredients(id);
        return new ResponseEntity<>(ingredientsItems, HttpStatus.OK);
    }
    @GetMapping("/restaurant/{id}/category")
    public ResponseEntity<List<IngredientCategory>> findIngredientCategoryByRestaurantId(@PathVariable Long id) throws Exception {
        List<IngredientCategory> ingredientsItems=ingredientsService.findIngredientCategoryByRestaurantId(id);
        return new ResponseEntity<>(ingredientsItems, HttpStatus.OK);
    }
}
