package com.interest.calculator.api.interest;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Payment {
    private Integer paymentNumber;
    private Double amount;
    private Date date;
}
