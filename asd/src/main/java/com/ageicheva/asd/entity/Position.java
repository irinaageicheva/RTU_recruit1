package com.ageicheva.asd.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class Position {
    @NonNull
    Integer receiptId;
    @NonNull
    String positionName;
    @NonNull
    Integer price;
    @NonNull
    Integer quantity;
    @Id
    private Date created = new Date();
}
