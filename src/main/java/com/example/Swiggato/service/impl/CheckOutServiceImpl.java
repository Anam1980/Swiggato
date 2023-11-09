package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.response.CheckOutResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.exception.EmptyCartException;
import com.example.Swiggato.models.*;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.repository.DeliveryPartnerRepository;
import com.example.Swiggato.repository.CheckOutRepository;
import com.example.Swiggato.repository.RestaurantRepository;
import com.example.Swiggato.service.CheckOutService;
import com.example.Swiggato.transformer.CheckOutTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CheckOutServiceImpl implements CheckOutService {

    final CustomerRepository customerRepository;
    final CheckOutRepository orderEntityRepo;

    final DeliveryPartnerRepository deliveryPartnerRepo;
    private final RestaurantRepository restaurantRespository;

    @Autowired
    public CheckOutServiceImpl(CustomerRepository customerRepository,
                        CheckOutRepository orderEntityRepo,
                        DeliveryPartnerRepository deliveryPartnerRepo,
                        RestaurantRepository restaurantRespository) {
        this.customerRepository = customerRepository;
        this.orderEntityRepo = orderEntityRepo;
        this.deliveryPartnerRepo = deliveryPartnerRepo;
        this.restaurantRespository = restaurantRespository;
    }

    public CheckOutResponse placeOrder(String customerMobile) throws CustomerNotFoundException {

        // verify the customer
        Customer customer = customerRepository.findByMobileNo(customerMobile);
        if(customer == null){
            throw new CustomerNotFoundException("Invalid mobile number!!!");
        }

        // verify if cart is empty or not
        Cart cart = customer.getCart();
        if(cart.getFoodItems().size()==0){
            throw new EmptyCartException("Sorry! Your cart is empty!!!");
        }

        // find a delivery partner to deliver. Randomly
        DeliveryPartner partner = deliveryPartnerRepo.findRandomDeliveryPartner();
        Restaurant restaurant = cart.getFoodItems().get(0).getMenu().getRestaurant();

        // prepare the order entity
        CheckOut order = CheckOutTransformer.prepareOrderEntity(cart);

        CheckOut savedOrder = orderEntityRepo.save(order);

        order.setCustomer(customer);
        order.setDeliveryPartner(partner);
        order.setRestaurant(restaurant);
        order.setFoodItems(cart.getFoodItems());

        customer.getOrders().add(savedOrder);
        partner.getOrders().add(savedOrder);
        restaurant.getOrders().add(savedOrder);

        for(FoodItem foodItem: cart.getFoodItems()){
            foodItem.setCart(null);
            foodItem.setCheckOut(savedOrder);
        }
        clearCart(cart);

        customerRepository.save(customer);
        deliveryPartnerRepo.save(partner);
        restaurantRespository.save(restaurant);

        // prepare orderresponse
        return CheckOutTransformer.CheckOutToCheckOutResponse(savedOrder);
    }

    private void clearCart(Cart cart) {
        cart.setCartTotal(0);
        cart.setFoodItems(new ArrayList<>());
    }
}
