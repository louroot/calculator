package com.interest.calculator.api.interest;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
public class CreditRequest {
    @NotNull(message = "amount is a required field")
    private Double amount;
    @NotNull(message = "terms is a required field")
    private Integer terms;
    @NotNull(message = "rate is a required field")
    private Double rate;
    
    @AssertTrue(message = "amount has to be greater than or equal to 1")
    public boolean getAmountCheck() {
        return amount >= 1;
    }
    
    @AssertTrue(message = "terms has to be greater than or equal to 1")
    public boolean getTermsCheck() {
        return terms >= 1;
    }
    
    @AssertTrue(message = "rate has to be greater than or equal to 1")
    public boolean getRateCheck() {
        return rate >= 1;
    }
}
