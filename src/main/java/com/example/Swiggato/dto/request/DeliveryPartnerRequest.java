package com.example.Swiggato.dto.request;

import com.example.Swiggato.Enum.Gender;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class DeliveryPartnerRequest {

    String name;

    String phoneNo;

    Gender gender;

    String email;

}
