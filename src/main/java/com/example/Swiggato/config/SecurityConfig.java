package com.example.Swiggato.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain (HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/customer/add")
                .permitAll()
                .requestMatchers("/restaurant/**")
                .permitAll()
                .requestMatchers("/cart/**")
                .permitAll()
                .requestMatchers("/checkOut/**")
                .permitAll()
                .requestMatchers("/menu/**")
                .permitAll()
                .requestMatchers("/foodItem/**")
                .permitAll()
                .requestMatchers("/deliveryPartner/**")
                .permitAll()
                .requestMatchers("/customer/find/mobile/**")
                .hasAnyRole("DELIVERY_PARTNER")
                .requestMatchers("/customer/get-most-ordered-customer")
                .hasAnyRole("DELIVERY_PARTNER")
                .requestMatchers("/customer/get-least-Ordered-female")
                .hasAnyRole("DELIVERY_PARTNER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

                return httpSecurity.build();
    }


    @Bean
    public UserDetailsService getUserDetailsService(){
       return new CustomUserDetailService();
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider getDaoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(getBCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

}
