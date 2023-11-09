package com.example.Swiggato.service.impl;

import com.example.Swiggato.dto.request.DeliveryPartnerRequest;
import com.example.Swiggato.models.DeliveryPartner;
import com.example.Swiggato.repository.DeliveryPartnerRepository;
import com.example.Swiggato.service.DeliveryPartnerService;
import com.example.Swiggato.transformer.DeliveryPartnerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryPartnerServiceImpl implements DeliveryPartnerService {

    final DeliveryPartnerRepository deliveryPartnerRepo;
    final JavaMailSender javaMailSender;

    @Autowired
    public DeliveryPartnerServiceImpl (DeliveryPartnerRepository deliveryPartnerRepo, JavaMailSender javaMailSender) {
        this.deliveryPartnerRepo = deliveryPartnerRepo;
        this.javaMailSender = javaMailSender;
    }

    public String addPartner(DeliveryPartnerRequest deliveryPartnerRequest) {

        //dto -> entity
        DeliveryPartner deliveryPartner = DeliveryPartnerTransformer.DeliveryPartnerRequestToDeliveryPartner(deliveryPartnerRequest);

        // save
        DeliveryPartner savedPartner = deliveryPartnerRepo.save(deliveryPartner);

        return "You have been successfully registered!!!";

    }

    @Override
    public DeliveryPartner getDeliveryPartnerWithHighDelivery() throws Exception {
        List<DeliveryPartner> deliveryPartnerList = deliveryPartnerRepo.findAll();

        int max = 0;
        DeliveryPartner highestPartner=null;
        for(DeliveryPartner partner : deliveryPartnerList){
            if(partner.getOrders().size()>max){
                max = partner.getOrders().size();
                highestPartner = partner;
            }
        }
        if(highestPartner==null){
            throw new Exception("No more Orders assigned");
        }
        return highestPartner;
    }

    @Override
    public List<DeliveryPartner> getDeliveryPartnerLessThan10delivery() {
        List<DeliveryPartner> deliveryPartnerList = deliveryPartnerRepo.findAll();

        List<DeliveryPartner>list = new ArrayList<>();

        for(DeliveryPartner partner : deliveryPartnerList){
            if(partner.getOrders().size()<10){
                String text = "Hi "+partner.getName()+"\nI hope this email finds you well. We appreciate your partnership with us. " +
                        "As part of our ongoing effort to optimize our delivery process, " +
                        "we have conducted a performance review and noticed that there have been fewer than 10 deliveries completed by your team.\n";
                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                simpleMailMessage.setFrom("acciojob49@gmail.com");
                simpleMailMessage.setTo(partner.getEmail());
                simpleMailMessage.setSubject("Important Notice: Delivery Performance Review");
                simpleMailMessage.setText(text);

               javaMailSender.send(simpleMailMessage);

                list.add(partner);
            }
        }

        return  list;
    }
}