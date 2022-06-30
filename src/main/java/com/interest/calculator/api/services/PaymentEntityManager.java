package com.interest.calculator.api.services;

import com.interest.calculator.api.db.PaymentEntityRepository;
import com.interest.calculator.api.db.entities.PaymentEntity;
import com.interest.calculator.api.interest.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PaymentEntityManager {
    private final PaymentEntityRepository paymentEntityRepository;
    
    public PaymentEntity save(Payment payment) {
        PaymentEntity paymentEntity = PaymentEntity.builder()
            .paymentNumber(payment.getPaymentNumber())
            .amount(payment.getAmount())
            .date(payment.getDate().toInstant())
            .build();
        return paymentEntityRepository.save(paymentEntity);
    }
}
