package com.ageicheva.asd.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class CustomerUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    String userName;
    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Receipt> receiptSet;
}
