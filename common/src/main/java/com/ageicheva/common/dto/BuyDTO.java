package com.ageicheva.common.dto;

import com.ageicheva.common.enums.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class BuyDTO {
    List<BuyDtoRow> positionsHistory;
    PaymentMethod paymentMethod;
    String userName;
    Integer userId;
    Integer shopId;
    String category;

}
