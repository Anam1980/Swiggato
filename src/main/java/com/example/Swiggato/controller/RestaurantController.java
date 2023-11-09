package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.MenuRequest;
import com.example.Swiggato.dto.request.RestaurantRequest;
import com.example.Swiggato.dto.response.RestaurantResponse;
import com.example.Swiggato.models.Menu;
import com.example.Swiggato.models.Restaurant;
import com.example.Swiggato.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    final RestaurantService restaurantService;


    /**
     * Constructor Injection
     * @param restaurantService  --> bean of restaurant Service
     */
    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/add")
    public ResponseEntity addRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        RestaurantResponse restaurantResponse = restaurantService.addRestaurant(restaurantRequest);
        return new ResponseEntity(restaurantResponse, HttpStatus.CREATED);
    }


    @PutMapping("/update/status")
    public ResponseEntity changeOpenedStatus(@RequestParam int id){
        String message = restaurantService.changeOpenedStatus(id);
        return new ResponseEntity(message,HttpStatus.ACCEPTED);
    }


    @PostMapping("/add/menu")
    public ResponseEntity addMenuItemToRestaurant(@RequestBody MenuRequest menuRequest){
        RestaurantResponse restaurantResponse = restaurantService.addMenuItemtToRestaurant(menuRequest);
        return new ResponseEntity(restaurantResponse,HttpStatus.CREATED);
    }

    // get menu of a restaurant
    @GetMapping("/getMenu")
    public ResponseEntity getMenu(@RequestParam("id") Integer id){
        try {
            List<Menu>menus = restaurantService.getMenu(id);
            return  new ResponseEntity<>(menus, HttpStatus.FOUND);
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // give all the restauratns who have served more than 'x' number of orders
     @GetMapping("/get-restaurant-morethan-orders/{order}")
    public ResponseEntity getRestaurantMoreThanOrder(@PathVariable Integer x){
         try {
             List<Restaurant> restaurantList = restaurantService.getRestaurantMoreThanOrder(x);
             return  new ResponseEntity<>(restaurantList, HttpStatus.FOUND);
         }
         catch (Exception e){
             return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
         }
     }

    // give the restaurants which have maximum number of items in their menu and which are opened also
    @GetMapping("/get-restaurants-max-menu")
    public ResponseEntity getRestaurantsMaxMenu(){
        try {
            Restaurant restaurant = restaurantService.getRestaurantsMaxMenu();
            return  new ResponseEntity<>(restaurant, HttpStatus.FOUND);
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
