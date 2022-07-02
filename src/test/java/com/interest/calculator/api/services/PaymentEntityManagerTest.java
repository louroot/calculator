package com.interest.calculator.api.services;

import com.interest.calculator.api.db.PaymentEntityRepository;
import com.interest.calculator.api.db.entities.PaymentEntity;
import com.interest.calculator.api.interest.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class PaymentEntityManagerTest {
    @Mock
    PaymentEntityRepository paymentEntityRepository;

    @InjectMocks
    PaymentEntityManager paymentEntityManager;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    public void save() {
        when(paymentEntityRepository.save(any(PaymentEntity.class))).thenReturn(PaymentEntity.builder().build());
        PaymentEntity saved = paymentEntityManager.save(Payment.builder().amount(10D).paymentNumber(1).date(new Date()).build());
        assertThat(saved).isNotNull();
        verify(paymentEntityRepository, times(1)).save(any(PaymentEntity.class));
    }
}