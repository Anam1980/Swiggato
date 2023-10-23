package com.example.Swiggato.dto.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckOutResponse {

    String checkOutId;//UUID

    double totalBill;

    String address;

    Date time;

    CustomerResponse customerResponse;

    RestaurantResponse restaurantResponse;

    List<FoodItemResponse> foodItemResponseList = new ArrayList<>();
}
