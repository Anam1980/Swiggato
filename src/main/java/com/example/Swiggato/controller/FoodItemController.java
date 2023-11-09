package com.example.Swiggato.controller;

import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foodItem")
public class FoodItemController {

    final FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }


    // give the food category which is ordered most
    @GetMapping("/get-foodCategory-ordered-most")
    public ResponseEntity getFoodCategoryOrderedMost(){
        try{
            FoodCategory foodCategory = foodItemService.getFoodCategoryOrderedMost();
            return new ResponseEntity<>(foodCategory, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
