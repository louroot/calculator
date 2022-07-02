package com.interest.calculator.api.interest;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CreditRequestTest {
    //Entity tests are sort of unnecessary, but wth
    @Test
    void getAmount() {
        CreditRequest creditRequest = new CreditRequest();
        double amount = 10D;
        creditRequest.setAmount(amount);
        assertThat(creditRequest.getAmount()).isEqualTo(amount);
    }

    @Test
    void getTerms() {
        CreditRequest creditRequest = new CreditRequest();
        int terms = 10;
        creditRequest.setTerms(terms);
        assertThat(creditRequest.getTerms()).isEqualTo(terms);
    }

    @Test
    void getDate() {
        CreditRequest creditRequest = new CreditRequest();
        double rate = 10D;
        creditRequest.setRate(rate);
        assertThat(creditRequest.getRate()).isEqualTo(rate);
    }
}