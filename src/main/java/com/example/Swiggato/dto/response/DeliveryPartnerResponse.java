package com.example.Swiggato.dto.response;

import com.example.Swiggato.Enum.Gender;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DeliveryPartnerResponse {
    String name;

    String phoneNo;

    Gender gender;

    String email;

    List<CheckOutResponse> checkOutResponseList = new ArrayList<>();
}
