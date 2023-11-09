package com.example.Swiggato.repository;

import com.example.Swiggato.models.CheckOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckOutRepository extends JpaRepository<CheckOut,Integer> {
}
