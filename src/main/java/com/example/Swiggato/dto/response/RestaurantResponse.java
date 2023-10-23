package com.example.Swiggato.dto.response;

import com.example.Swiggato.Enum.RestaurantCategory;

import java.util.ArrayList;
import java.util.List;

public class RestaurantResponse {

    String name;

    boolean isOpened;

    String location;

    RestaurantCategory restaurantCategory;

    List<MenuResponse> menuResponseList = new ArrayList<>();
}
