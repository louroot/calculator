package com.interest.calculator.api.interest;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
public class Payment {
    private Integer paymentNumber;
    private Double amount;
    private Date date;

    public Payment(Integer paymentNumber, Double amount, Date date) {
        this.paymentNumber = paymentNumber;
        this.amount = amount;
        this.date = date;
    }
}
