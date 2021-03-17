package com.ageicheva.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer receiptId;
    Integer userId;
    Integer shopId;
    String userName;
    String category;
    Integer fullPrice;
    @Builder.Default
    Date date = new Date();
    @OneToMany(mappedBy = "receiptId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Position> positions;
}
