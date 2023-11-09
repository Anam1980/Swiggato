package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.response.FoodItemResponse;
import com.example.Swiggato.dto.response.CheckOutResponse;
import com.example.Swiggato.models.Cart;
import com.example.Swiggato.models.FoodItem;
import com.example.Swiggato.models.CheckOut;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CheckOutTransformer {

    public static CheckOut prepareOrderEntity(Cart cart){
        return CheckOut.builder()
                .orderId(String.valueOf(UUID.randomUUID()))
                .orderTotal(cart.getCartTotal())
                .build();
    }

    public static CheckOutResponse CheckOutToCheckOutResponse(CheckOut savedOrder) {

        List<FoodItemResponse> foodResponseList = new ArrayList<>();
        for(FoodItem food: savedOrder.getFoodItems()){
            FoodItemResponse foodResponse = FoodItemResponse.builder()
                    .dishName(food.getMenu().getDishName())
                    .price(food.getMenu().getPrice())
                    .category(food.getMenu().getFoodCategory())
                    .veg(food.getMenu().isVeg())
                    .quantityAdded(food.getRequiredQuantity())
                    .build();

            foodResponseList.add(foodResponse);
        }

        return CheckOutResponse.builder()
                .orderId(savedOrder.getOrderId())
                .orderTime(savedOrder.getOrderTime())
                .orderTotal(savedOrder.getOrderTotal())
                .customerName(savedOrder.getCustomer().getName())
                .customerMobile(savedOrder.getCustomer().getMobileNo())
                .deliveryPartnerName(savedOrder.getDeliveryPartner().getName())
                .deliveryPartnerMobile(savedOrder.getDeliveryPartner().getMobileNo())
                .restaurantName(savedOrder.getRestaurant().getName())
                .foodResponses(foodResponseList)
                .build();
    }
}

