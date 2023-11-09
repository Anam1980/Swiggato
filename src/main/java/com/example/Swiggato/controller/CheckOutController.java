package com.example.Swiggato.controller;

import com.example.Swiggato.dto.response.CheckOutResponse;
import com.example.Swiggato.service.CheckOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkOut")
public class CheckOutController {

    final CheckOutService checkOutService;

    @Autowired
    public CheckOutController(CheckOutService checkOutService) {
        this.checkOutService = checkOutService;
    }

    @PostMapping("/place/mobile/{mobile}")
    public ResponseEntity placeOrder(@PathVariable("mobile") String customerMobile){

        try{
            CheckOutResponse checkOutResponse = checkOutService.placeOrder(customerMobile);
            return new ResponseEntity(checkOutResponse,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}