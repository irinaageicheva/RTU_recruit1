package com.ageicheva.shops.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class PositionHistory {
    @Id
    String positionName;
    Integer price;
    Integer quantity;

    @Builder.Default
    Date date = new Date();


}
