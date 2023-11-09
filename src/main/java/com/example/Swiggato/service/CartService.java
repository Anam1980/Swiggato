package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.FoodItemRequest;
import com.example.Swiggato.dto.response.CartStatusResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.exception.MenuItemNotFoundException;

public interface CartService  {
    CartStatusResponse addFoodItemToCart(FoodItemRequest foodRequest) throws CustomerNotFoundException, MenuItemNotFoundException;
}
