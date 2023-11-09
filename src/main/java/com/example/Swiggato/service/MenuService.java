package com.example.Swiggato.service;

import com.example.Swiggato.Enum.FoodCategory;
import com.example.Swiggato.dto.request.MenuRequest;
import com.example.Swiggato.dto.response.MenuResponse;

import java.util.List;

public interface MenuService {

    List<String> getFoodItemOfCategory(FoodCategory foodCategory);
}
