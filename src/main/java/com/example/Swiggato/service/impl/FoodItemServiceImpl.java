package com.example.Swiggato.service.impl;

import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.models.CheckOut;
import com.example.Swiggato.models.FoodItem;
import com.example.Swiggato.repository.CheckOutRepository;
import com.example.Swiggato.repository.FoodItemRepository;
import com.example.Swiggato.service.FoodItemService;

import java.util.List;

public class FoodItemServiceImpl implements FoodItemService {

    final CheckOutRepository checkOutRepository;

    public FoodItemServiceImpl(CheckOutRepository checkOutRepository) {

        this.checkOutRepository = checkOutRepository;
    }

    @Override
    public FoodCategory getFoodCategoryOrderedMost() {
        List<CheckOut>checkOutList = checkOutRepository.findAll();

        int  STARTER = 0;
        int DESSERT = 0;
        int MAIN_COURSE = 0;
        int DRINK = 0;

        for(CheckOut checkOut : checkOutList){
            for(FoodItem foodItem : checkOut.getFoodItems()) {
                if (foodItem.getMenu().equals(FoodCategory.STARTER)) {
                    STARTER++;
                } else if (foodItem.getMenu().equals(FoodCategory.DESSERT)) {
                    DESSERT++;
                } else if (foodItem.getMenu().equals(FoodCategory.DRINK)) {
                    DRINK++;
                } else {
                    MAIN_COURSE++;
                }
            }
        }
        int max = Math.max(STARTER,Math.max(DESSERT, Math.max(DRINK, MAIN_COURSE)));

        if(max==STARTER){
            return FoodCategory.STARTER;
        }
        else if(max==MAIN_COURSE){
            return FoodCategory.MAIN_COURSE;
        }
        else if(max == DESSERT){
            return FoodCategory.DESSERT;
        }
        else{
            return FoodCategory.DRINK;
        }
    }
}
