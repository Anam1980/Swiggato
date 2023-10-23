package com.example.Swiggato.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    @Size(min = 2, message = "{validation.name.size.too_short}")
    @Size(max = 40, message = "{validation.name.size.too_long}")
    String name;


    @Column(unique = true)
    @Email
    String email;


    @Column(unique = true, nullable = false)
    @Size(max = 10, min=10)
    String phoneNo;

    String location;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<CheckOut> checkOutList = new ArrayList<>();//many orders (child)

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;//one cart(child)
}
