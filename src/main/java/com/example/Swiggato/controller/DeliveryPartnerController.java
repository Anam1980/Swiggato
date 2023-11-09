package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.DeliveryPartnerRequest;
import com.example.Swiggato.models.DeliveryPartner;
import com.example.Swiggato.service.DeliveryPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deliveryPartner")
public class DeliveryPartnerController {
    final DeliveryPartnerService deliveryPartnerService;

    @Autowired
    public DeliveryPartnerController(DeliveryPartnerService deliveryPartnerService) {
        this.deliveryPartnerService = deliveryPartnerService;
    }

    @PostMapping("/add")
    public ResponseEntity addDeliveryPartner(@RequestBody DeliveryPartnerRequest deliveryPartnerRequest){

        String message = deliveryPartnerService.addPartner(deliveryPartnerRequest);
        return new ResponseEntity(message, HttpStatus.CREATED);
    }

    // give delivery partner with highest number of deliveries
    @GetMapping("/get-highest-order-partner")
    public ResponseEntity getDeliveryPartnerWithHighDelivery(){
        try{
            DeliveryPartner deliveryPartner = deliveryPartnerService.getDeliveryPartnerWithHighDelivery();
            return new ResponseEntity<>(deliveryPartner,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // send an email to all the partners who have done less than 10 delivery
    @GetMapping("/get-partners-lessThan10-delivery")
    public ResponseEntity getDeliveryPartnerLessThan10delivery(){
        try{
            List<DeliveryPartner>deliveryPartners = deliveryPartnerService.getDeliveryPartnerLessThan10delivery();
            return new ResponseEntity<>(deliveryPartners, HttpStatus.OK);
        }
        catch (Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
