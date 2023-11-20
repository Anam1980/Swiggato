package com.example.Swiggato.config;

import com.example.Swiggato.models.DeliveryPartner;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

 public class UserDetailCreator implements UserDetails {
    String username;//this not change as getter in implementation is getUsername
    String password;
    List<GrantedAuthority> grantedAuthorities = new ArrayList<>();


    public UserDetailCreator(DeliveryPartner deliveryPartner) {
        this.username = deliveryPartner.getUsername();
        this.password = deliveryPartner.getPassword();
        grantedAuthorities = new ArrayList<>();

        String[] roles = deliveryPartner.getRole().split(",");

        for(String role : roles){
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(role);//grant this role
            grantedAuthorities.add(simpleGrantedAuthority);//add to granted aithorities list
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username ;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
