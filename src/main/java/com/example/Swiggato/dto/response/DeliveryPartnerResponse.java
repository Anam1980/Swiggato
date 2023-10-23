package com.example.Swiggato.dto.response;

import com.example.Swiggato.Enum.Gender;

import java.util.ArrayList;
import java.util.List;

public class DeliveryPartnerResponse {
    String name;

    String phoneNo;

    Gender gender;

    List<CheckOutResponse> checkOutResponseList = new ArrayList<>();
}
