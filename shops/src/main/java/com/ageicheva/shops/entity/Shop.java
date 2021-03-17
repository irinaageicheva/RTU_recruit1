package com.ageicheva.shops.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String name;
    String address;
    String phoneNumber;
    @OneToMany(mappedBy = "shopId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Set<Position> positionSet;
}
