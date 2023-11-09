package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.exception.CustomerNotFoundException;
import com.example.Swiggato.models.Cart;
import com.example.Swiggato.models.Customer;
import com.example.Swiggato.repository.CustomerRepository;
import com.example.Swiggato.service.CustomerService;
import com.example.Swiggato.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerResponse addCustomer(CustomerRequest customerRequest) {

        // dto -> customer model
        Customer customer = CustomerTransformer.CustomerRequestToCustomer(customerRequest);

        //allocate a cart
        Cart cart = Cart.builder()
                .cartTotal(0)
                .customer(customer)
                .build();

        customer.setCart(cart);

        // save both customer and cart
        Customer savedCustomer = customerRepository.save(customer);  // saves both customer and cart

        // prepare response Dto
        return CustomerTransformer.CustomerToCustomerResponse(savedCustomer);
    }

    @Override
    public CustomerResponse getMostOrderedCustomer() {
        List<Customer> customerList =  customerRepository.findAll();
        Collections.sort(customerList, (a, b)-> b.getOrders().size()-a.getOrders().size());

        Customer customer = customerList.get(0);
        CustomerResponse customerResponse = CustomerTransformer.CustomerToCustomerResponse(customer);

        return customerResponse;
    }

    @Override
    public CustomerResponse getLeastOrderedFemale() {
        return null;
    }

    public CustomerResponse findCustomerByMobile(String mobile) throws CustomerNotFoundException {

        Customer customer = customerRepository.findByMobileNo(mobile);
        if(customer==null){
            throw new CustomerNotFoundException("Invalid mobile no!!!");
        }
        return CustomerTransformer.CustomerToCustomerResponse(customer);
    }
}