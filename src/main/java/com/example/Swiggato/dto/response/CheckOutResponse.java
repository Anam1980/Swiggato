package com.example.Swiggato.dto.response;

import com.example.Swiggato.models.Customer;
import com.example.Swiggato.models.DeliveryPartner;
import com.example.Swiggato.models.FoodItem;
import com.example.Swiggato.models.Restaurant;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutResponse {

    String orderId;  // UUID

    double orderTotal;

    Date orderTime;

    String customerName;

    String customerMobile;

    String deliveryPartnerName;

    String deliveryPartnerMobile;

    String restaurantName;

    List<FoodItemResponse> foodResponses;
}