package com.example.Swiggato.dto.request;

import com.example.Swiggato.Enum.RestaurantCategory;
import com.example.Swiggato.Enum.RestaurantCategory;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {

    String name;

    String location;

    RestaurantCategory restrauntCategory;

    String contactNumber;

}