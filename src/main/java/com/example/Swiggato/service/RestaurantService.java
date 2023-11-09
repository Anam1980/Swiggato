package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.MenuRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.exception.RestaurantNotFoundException;
import com.example.Swiggato.models.Menu;
import com.example.Swiggato.models.Restaurant;

import java.util.List;

public interface RestaurantService {
    RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest);

    String changeOpenedStatus(int id);

    RestaurantResponse addMenuItemtToRestaurant(MenuRequest menuRequest);

    List<Menu> getMenu(Integer id) ;

    List<Restaurant> getRestaurantMoreThanOrder(Integer x);

    Restaurant getRestaurantsMaxMenu();
}
