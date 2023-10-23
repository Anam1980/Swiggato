package com.example.Swiggato.dto.response;

import com.example.Swiggato.Enum.MenuType;

import java.util.ArrayList;
import java.util.List;

public class MenuResponse {
    int restaurantId;

    String name;

    MenuType menuType;

    List<FoodItemResponse>foodItemResponses = new ArrayList<>();
}
