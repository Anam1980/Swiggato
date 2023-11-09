package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.DeliveryPartnerRequest;
import com.example.Swiggato.models.DeliveryPartner;

import java.util.List;

public interface DeliveryPartnerService {
    String addPartner(DeliveryPartnerRequest deliveryPartnerRequest);

    DeliveryPartner getDeliveryPartnerWithHighDelivery() throws Exception;

    List<DeliveryPartner> getDeliveryPartnerLessThan10delivery();
}
