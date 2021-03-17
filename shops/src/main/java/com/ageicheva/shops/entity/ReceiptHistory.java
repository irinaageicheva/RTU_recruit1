package com.ageicheva.shops.entity;

import com.ageicheva.common.enums.PaymentMethod;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ReceiptHistory {
    @Id
    @Builder.Default
    Date date = new Date();

    @OneToMany(mappedBy = "date", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<PositionHistory> positionsHistory;


    PaymentMethod paymentMethod;



    String userName;


    Integer shopId;
    String category;
}
