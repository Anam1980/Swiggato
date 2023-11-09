package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.MenuRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.exception.RestaurantNotFoundException;
import com.example.Swiggato.models.Menu;
import com.example.Swiggato.models.Restaurant;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.service.RestaurantService;
import com.example.Swiggato.transformer.MenuTransformer;
import com.example.Swiggato.transformer.RestaurantTransformer;
import com.example.Swiggato.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class RestaurantServiceImpl implements RestaurantService {

    final RestaurantRepository restaurantRespository;
    final ValidationUtils validationUtils;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRespository,
                             ValidationUtils validationUtils) {
        this.restaurantRespository = restaurantRespository;
        this.validationUtils = validationUtils;
    }

    public RestaurantResponse addRestaurant(RestaurantRequest restaurantRequest) {

        // dto -> model
        Restaurant restaurant = RestaurantTransformer.RestaurantRequestToRestaurant(restaurantRequest);
        //persist/save the model in db
        Restaurant savedRestaurant = restaurantRespository.save(restaurant);
        // prepare response dto from model
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);
    }

    public String changeOpenedStatus(int id) {

        //check whether id is valid or not
        if(!validationUtils.validateRestaurantId(id)){
            throw new RestaurantNotFoundException("Restaurant doesn't exist!!");
        }

        Restaurant restaurant = restaurantRespository.findById(id).get();
        restaurant.setOpened(!restaurant.isOpened());
        restaurantRespository.save(restaurant);

        if(restaurant.isOpened()){
            return "Restaurant is opened now!!!";
        }

        return "Restaurant is closed";
    }

    public RestaurantResponse addMenuItemtToRestaurant(MenuRequest menuRequest) {

        // check reataurant is valid or not
        if(!validationUtils.validateRestaurantId(menuRequest.getRestaurantId())){
            throw new RestaurantNotFoundException("Restaurant doesn't exist!!");
        }

        Restaurant restaurant = restaurantRespository.findById(menuRequest.getRestaurantId()).get();
        // make food entity
        Menu menuItem = MenuTransformer.MenuRequestToMenuItem(menuRequest);
        menuItem.setRestaurant(restaurant);

        restaurant.getAvailableMenuItems().add(menuItem);

        // save rest and food
        Restaurant savedRestaurant = restaurantRespository.save(restaurant);

        // prepare response
        return RestaurantTransformer.RestaurantToRestaurantResponse(savedRestaurant);

    }

    @Override
    public List<Menu> getMenu(Integer id)  {
        Optional<Restaurant> restaurantOptional = restaurantRespository.findById(id);
        if(restaurantOptional
                .isPresent()){
            Restaurant restaurant = restaurantOptional.get();
            List<Menu>menus = restaurant.getAvailableMenuItems();
            return  menus;
        }
        else{
            throw new RestaurantNotFoundException("Invalid restaurant Id");
        }
    }

    @Override
    public List<Restaurant> getRestaurantMoreThanOrder(Integer x) {
        List<Restaurant>restaurantList = restaurantRespository.findAll();
        List<Restaurant> list = new ArrayList<>();
        for(Restaurant restaurant : restaurantList){
            if(restaurant.getOrders().size()>x){
                list.add(restaurant);
            }
        }
        return list;
    }

    @Override
    public Restaurant getRestaurantsMaxMenu() {
        List<Restaurant>restaurantList = restaurantRespository.findAll();
        Restaurant restaurant = null;
        int max = 0;
        for(Restaurant restaurant1 : restaurantList){
            if(restaurant1.isOpened() && restaurant1.getAvailableMenuItems().size()>max){
                max = restaurant1.getAvailableMenuItems().size();
                restaurant = restaurant1;
            }
        }
        return restaurant;
    }
}
