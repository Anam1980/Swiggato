package com.example.Swiggato.service;

import com.example.Swiggato.dto.response.CheckOutResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;

public interface CheckOutService {
    CheckOutResponse placeOrder(String customerMobile) throws CustomerNotFoundException;
}
