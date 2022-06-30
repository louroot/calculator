package com.interest.calculator.api.interest;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreditRequest {
    @NotNull(message = "amount is a required field")
    private Double amount;
    @NotNull(message = "terms is a required field")
    private Integer terms;
    @NotNull(message = "rate is a required field")
    private Double rate;
}
