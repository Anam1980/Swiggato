package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.response.FoodItemResponse;
import com.example.Swiggato.models.FoodItem;

public class FoodItemTransformer {

    public static FoodItemResponse FoodToFoodResponse(FoodItem food){
        return FoodItemResponse.builder()
                .dishName(food.getMenu().getDishName())
                .price(food.getMenu().getPrice())
                .category(food.getMenu().getFoodCategory())
                .veg(food.getMenu().isVeg())
                .quantityAdded(food.getRequiredQuantity())
                .build();
    }
}