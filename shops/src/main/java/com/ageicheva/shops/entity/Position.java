package com.ageicheva.shops.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Position {
    @Id
    String positionName;
    Integer shopId;
    Integer price;
    String category;
    Integer quantity;
}
