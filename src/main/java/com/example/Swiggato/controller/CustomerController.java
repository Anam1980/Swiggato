package com.example.Swiggato.controller;

import com.example.Swiggato.dto.request.CustomerRequest;
import com.example.Swiggato.dto.response.CustomerResponse;
import com.example.Swiggato.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;  // field injection

    /*
    Consturctor Injection ----> Always use in enterprise applications
     */
//    final CustomerService customerService;
//
//    @Autowired
//    public CustomerController(CustomerService customerService){
//        this.customerService = customerService;
//    }

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.addCustomer(customerRequest);
        return new ResponseEntity(customerResponse, HttpStatus.CREATED);
    }

    @GetMapping("/find/mobile/{mobile}")
    public ResponseEntity getCustomerByMobile(@PathVariable("mobile") String mobile){
        try{
            CustomerResponse customerResponse = customerService.findCustomerByMobile(mobile);
            return new ResponseEntity(customerResponse,HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // get the customer with most number of orders
    @GetMapping("/get-most-ordered-customer")
    public ResponseEntity getMostOrderedCustomer(){
        try{
            CustomerResponse customerResponse = customerService.getMostOrderedCustomer();
            return new ResponseEntity<>(customerResponse, HttpStatus.FOUND);
        }
        catch(Exception e){
            return  new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // get the female customer with least number of orders
    @GetMapping("/get-least-Ordered-female")
    public ResponseEntity getLeastOrderedFemale(){
        try{
            CustomerResponse customerResponse = customerService.getLeastOrderedFemale();
            return  new ResponseEntity<>(customerResponse, HttpStatus.FOUND);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
