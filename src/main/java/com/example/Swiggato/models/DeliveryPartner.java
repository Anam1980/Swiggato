package com.example.Swiggato.models;

import com.example.Swiggato.Enum.Gender;
import jakarta.persistence.*;
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
public class DeliveryPartner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    String name;

    @Column(unique = true,nullable = false)
    @Size(max = 10, min = 10)
    String phoneNo;

    Gender gender;

    @OneToMany(mappedBy = "deliveryPartner", cascade = CascadeType.ALL)
    List<CheckOut>checkOutList = new ArrayList<>();
}
