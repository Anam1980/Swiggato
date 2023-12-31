package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.FoodItemRequest;
import com.example.Swiggato.dto.response.CartStatusResponse;
import com.example.Swiggato.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {
    final CartService cartService;//testing

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity addFoodItemToCart(@RequestBody FoodItemRequest foodRequest){
        try {
            CartStatusResponse cartStatusResponse = cartService.addFoodItemToCart(foodRequest);
            return new ResponseEntity(cartStatusResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
