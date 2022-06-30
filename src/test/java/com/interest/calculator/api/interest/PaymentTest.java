package com.interest.calculator.api.interest;

import org.junit.jupiter.api.Test;
import java.util.Date;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {

    @Test
    void getPaymentNumber() {
        Payment payment = new Payment();
        int paymentNumber = 10;
        payment.setPaymentNumber(paymentNumber);
        assertThat(payment.getPaymentNumber()).isEqualTo(paymentNumber);
    }

    @Test
    void getAmount() {
        Payment payment = new Payment();
        double amount = 10D;
        payment.setAmount(amount);
        assertThat(payment.getAmount()).isEqualTo(amount);
    }

    @Test
    void getDate() {
        Payment payment = new Payment();
        Date date = new Date();
        payment.setDate(date);
        assertThat(payment.getDate()).isEqualTo(date);
    }
}