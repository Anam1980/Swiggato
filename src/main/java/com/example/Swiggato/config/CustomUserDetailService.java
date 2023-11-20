package com.example.Swiggato.config;

import com.example.Swiggato.models.DeliveryPartner;
import com.example.Swiggato.repository.DeliveryPartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    DeliveryPartnerRepository deliveryPartnerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DeliveryPartner deliveryPartner = deliveryPartnerRepository.findByUsername(username);
        if(deliveryPartner == null){
            throw  new UsernameNotFoundException("Invalid username");
        }

        return new UserDetailCreator(deliveryPartner);
    }
}
