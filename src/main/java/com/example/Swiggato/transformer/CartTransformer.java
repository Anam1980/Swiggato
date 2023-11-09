package com.example.Swiggato.transformer;



import com.example.Swiggato.dto.response.CartResponse;
import com.example.Swiggato.models.Cart;

import java.util.ArrayList;

public class CartTransformer {

    public static CartResponse CartToCartResponse(Cart cart){
        return CartResponse.builder()
                .cartTotal(cart.getCartTotal())
                .foodItems(new ArrayList<>())
                .build();
    }
}
