package com.example.Swiggato.models;

import com.example.Swiggato.Enum.MenuType;
import jakarta.persistence.*;
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
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;

    String name;

    MenuType menuType;

    @OneToMany(mappedBy = "menu", cascade = CascadeType.ALL)
    List<FoodItem>foodItemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Restaurant restaurant;

}
