package com.example.Swiggato.service;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.exception.MenuItemNotFoundException;

public interface CustomerService {
    CustomerResponse findCustomerByMobile(String mobile) throws CustomerNotFoundException;

    CustomerResponse addCustomer(CustomerRequest customerRequest);

    CustomerResponse getMostOrderedCustomer();

    CustomerResponse getLeastOrderedFemale();
}
