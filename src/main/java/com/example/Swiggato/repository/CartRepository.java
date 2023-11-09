package com.example.Swiggato.repository;

import com.example.Swiggato.models.Cart;
import com.example.Swiggato.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
}
