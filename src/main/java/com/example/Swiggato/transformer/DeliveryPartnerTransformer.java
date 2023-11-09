package com.example.Swiggato.transformer;

import com.example.Swiggato.dto.request.DeliveryPartnerRequest;
import com.example.Swiggato.models.DeliveryPartner;

import java.util.ArrayList;

public class DeliveryPartnerTransformer {

    public static DeliveryPartner DeliveryPartnerRequestToDeliveryPartner(
            DeliveryPartnerRequest deliveryPartnerRequest){

        return DeliveryPartner.builder()
                .name(deliveryPartnerRequest.getName())
                .mobileNo(deliveryPartnerRequest.getPhoneNo())
                .gender(deliveryPartnerRequest.getGender())
                .email(deliveryPartnerRequest.getEmail())
                .orders(new ArrayList<>())
                .build();
    }
}
