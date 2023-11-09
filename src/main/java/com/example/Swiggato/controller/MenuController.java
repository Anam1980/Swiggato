package com.example.Swiggato.controller;

import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.dto.request.MenuRequest;
import com.example.Swiggato.dto.response.CartStatusResponse;
import com.example.Swiggato.dto.response.MenuResponse;
import com.example.Swiggato.models.FoodItem;
import com.example.Swiggato.models.Menu;
import com.example.Swiggato.models.Restaurant;
import com.example.Swiggato.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    final MenuService menuService;

    @Autowired
    public MenuController(MenuService menuService){
        this.menuService=menuService;
    }


// get all foods of a particualr category
    @GetMapping("/get-foodItem")
    public ResponseEntity getFoodItemOfCategory(@RequestParam FoodCategory foodCategory){
        try{
            List<String> menus = menuService.getFoodItemOfCategory(foodCategory);
            return new ResponseEntity<>(menus, HttpStatus.FOUND);
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


// get all MAIN_COURSE items with price above x rupees from a particular restaurant.
@GetMapping("/get-main+course-price+above/{restaurantId}/{price}")
public ResponseEntity getMainCourseAbovePrice(@PathVariable Integer restaurantId, @PathVariable Integer price){
    try{
        List<Menu> menuList = menuService.getMainCourseAbovePrice(restaurantId, price);
        return new ResponseEntity<>(menuList, HttpStatus.OK);
    }
    catch (Exception e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

// get all veg foods of a restaurant
@GetMapping("/get-veg+foodItem/{restaurantId}")
public ResponseEntity getVegFood(@PathVariable Integer restaurantId){
    try{
        List<String> foodList = menuService.getVegFood(restaurantId);
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }
    catch (Exception e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

// get all non veg foods of a restaurant
@GetMapping("/get-nonVeg+foodItem/{restaurantId}")
public ResponseEntity getNonVegFood(@PathVariable Integer restaurantId){
    try{
        List<String> foodList = menuService.getNonVegFood(restaurantId);
        return  new ResponseEntity<>(foodList, HttpStatus.OK);
    }
    catch (Exception e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

// Get cheapest 5 food items of a partiuclar restaurant
@GetMapping("/get-cheap-5+foodItem/{restaurantId}")
public ResponseEntity getCheap5FoodItem(@PathVariable Integer restaurantId){
    try{
        List<String>foodList = menuService.getCheap5FoodItem(restaurantId);
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }
    catch (Exception e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

// Get costliest 5 food items of a partiuclar restaurant
@GetMapping("/get-costly+foodItem/{restaurantId}")
public ResponseEntity getCostlyFoodItem(@PathVariable Integer restaurantId){
    try{
        List<String>foodList = menuService.getCostlyFoodItem(restaurantId);
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }
    catch (Exception e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}

// Get costliest 5 food items of a partiuclar catgeory -> name fo dish and rest which serves that dish
@GetMapping("/get-costly+5foodItem/{foodCategory}")
public ResponseEntity get5CostlyFoodItemOfCategory(@RequestParam FoodCategory foodCategory){
    try{
        List<String>foodList = menuService.get5CostlyFoodItemOfCategory(foodCategory);
        return new ResponseEntity<>(foodList, HttpStatus.OK);
    }
    catch (Exception e){
        return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
}
