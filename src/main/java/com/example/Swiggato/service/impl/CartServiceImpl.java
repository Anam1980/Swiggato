package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.FoodItemRequest;
import com.example.Swiggato.dto.response.CartStatusResponse;
import com.example.Swiggato.dto.response.FoodItemResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.exception.MenuItemNotFoundException;
import com.example.Swiggato.models.*;
import com.example.Swiggato.models.Menu;
import com.example.Swiggato.repository.CartRepository;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.repository.FoodItemRepository;
import com.example.Swiggato.repository.MenuRepository;
import com.example.Swiggato.service.CartService;
import com.example.Swiggato.transformer.FoodItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    final CustomerRepository customerRepository;
    final MenuRepository menuRepository;
    final CartRepository cartRepository;
    final FoodItemRepository foodRepo;

    @Autowired
    public CartServiceImpl(CustomerRepository customerRepository,
                       MenuRepository menuRepository,
                       CartRepository cartRepository,
                       FoodItemRepository foodRepo) {
        this.customerRepository = customerRepository;
        this.menuRepository = menuRepository;
        this.cartRepository = cartRepository;
        this.foodRepo = foodRepo;
    }

    public CartStatusResponse addFoodItemToCart(FoodItemRequest foodRequest) throws CustomerNotFoundException, MenuItemNotFoundException {

        Customer customer = customerRepository.findByMobileNo(foodRequest.getCustomerMobile());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exisit");
        }

        Optional<Menu> menuItemOptional = menuRepository.findById(foodRequest.getMenuId());
        if(menuItemOptional.isEmpty()){
            throw new MenuItemNotFoundException("Item not available in the restaurant!!!");
        }

        Menu menuItem = menuItemOptional.get();
        if(!menuItem.getRestaurant().isOpened() || !menuItem.isAvailable()) {
            throw new MenuItemNotFoundException("Given dish is out of stock for now!!!");
        }

        Cart cart = customer.getCart();

        // having item from same restaurant
        if(cart.getFoodItems().size()!=0){
            Restaurant currRestaurant = cart.getFoodItems().get(0).getMenu().getRestaurant();
            Restaurant newRestaurant = menuItem.getRestaurant();

            if(!currRestaurant.equals(newRestaurant)){
                List<FoodItem> foodItems = cart.getFoodItems();
                for(FoodItem foodItem: foodItems) {
                    foodItem.setCart(null);
                    foodItem.setCheckOut(null);
                    foodItem.setMenu(null);
                }
                cart.setCartTotal(0);
                cart.getFoodItems().clear();
                foodRepo.deleteAll(foodItems);
            }
        }

        boolean alreadyExists = false;
        FoodItem savedFoodItem = null;
        if(cart.getFoodItems().size()!=0){
            for(FoodItem foodItem: cart.getFoodItems()){
                if(foodItem.getMenu().getId()==menuItem.getId()){
                    savedFoodItem = foodItem;
                    int curr = foodItem.getRequiredQuantity();
                    foodItem.setRequiredQuantity(curr+foodRequest.getRequiredQuantity());
                    alreadyExists = true;
                    break;
                }
            }
        }

        if(!alreadyExists){
            FoodItem foodItem = FoodItem.builder()
                    .menu(menuItem)
                    .requiredQuantity(foodRequest.getRequiredQuantity())
                    .totalCost(foodRequest.getRequiredQuantity()*menuItem.getPrice())
                    .build();

            savedFoodItem = foodRepo.save(foodItem);
            cart.getFoodItems().add(savedFoodItem);
            menuItem.getFoodItems().add(savedFoodItem);
            savedFoodItem.setCart(cart);
        }

        double cartTotal = 0;
        for(FoodItem food: cart.getFoodItems()){
            cartTotal += food.getRequiredQuantity()*food.getMenu().getPrice();
        }

        cart.setCartTotal(cartTotal);
        // save
        Cart savedCart = cartRepository.save(cart);
        Menu savedMenuItem = menuRepository.save(menuItem);

        // prepare
        List<FoodItemResponse> foodResponseList = new ArrayList<>();
        for(FoodItem food: cart.getFoodItems()){
            foodResponseList.add(FoodItemTransformer.FoodToFoodResponse(food));
        }

        return CartStatusResponse.builder()
                .customerName(savedCart.getCustomer().getName())
                .customerMobile(savedCart.getCustomer().getMobileNo())
                .customerAddress(savedCart.getCustomer().getAddress())
                .foodList(foodResponseList)
                .restaurantName(savedMenuItem.getRestaurant().getName())
                .cartTotal(cartTotal)
                .build();

    }
}