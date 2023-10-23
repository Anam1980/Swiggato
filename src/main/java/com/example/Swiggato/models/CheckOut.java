package com.example.Swiggato.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    String checkOutId;//UUID

    double totalBill;

    String address;

    Date time;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "checkOut", cascade = CascadeType.ALL)
    List<FoodItem> foodItemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

    @ManyToOne
    @JoinColumn
    DeliveryPartner deliveryPartner;

}
