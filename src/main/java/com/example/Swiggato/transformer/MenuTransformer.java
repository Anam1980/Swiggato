package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.MenuRequest;
import com.example.Swiggato.dto.response.MenuResponse;
import com.example.Swiggato.models.Menu;

public class MenuTransformer {

    public static Menu MenuRequestToMenuItem(MenuRequest menuRequest){
        return Menu.builder()
                .dishName(menuRequest.getDishName())
                .price(menuRequest.getPrice())
                .foodCategory(menuRequest.getCategory())
                .veg(menuRequest.isVeg())
                .available(menuRequest.isAvailable())
                .build();
    }

    public static MenuResponse MenuItemToMenuResponse(Menu menuItem){
        return MenuResponse.builder()
                .dishName(menuItem.getDishName())
                .price(menuItem.getPrice())
                .veg(menuItem.isVeg())
                .category(menuItem.getFoodCategory())
                .build();
    }
}