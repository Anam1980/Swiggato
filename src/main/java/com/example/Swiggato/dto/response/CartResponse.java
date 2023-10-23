package com.example.Swiggato.dto.response;

import com.example.Swiggato.models.FoodItem;

import java.util.ArrayList;
import java.util.List;

public class CartResponse {

    int totalItems;

    List<MenuResponse> foodItemList = new ArrayList<>();
}
