package com.example.Swiggato.repository;


import com.example.Swiggato.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Integer> {
}
