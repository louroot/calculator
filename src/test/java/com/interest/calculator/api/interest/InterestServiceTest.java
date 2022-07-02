package com.interest.calculator.api.interest;

import com.interest.calculator.api.db.entities.CreditEntity;
import com.interest.calculator.api.db.entities.PaymentEntity;
import com.interest.calculator.api.exceptions.BadRequestException;
import com.interest.calculator.api.services.CreditEntityManager;
import com.interest.calculator.api.services.PaymentEntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class InterestServiceTest {
    @Mock
    CreditEntityManager creditEntityManager;

    @Mock
    PaymentEntityManager paymentEntityManager;

    @InjectMocks
    InterestService interestService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    public void calculateInterest() throws BadRequestException {
        CreditRequest creditRequest = CreditRequest.builder().amount(1000D).terms(1).rate(5D).build();
        CreditEntity creditEntity = CreditEntity.builder().amount(1000D).terms(1).rate(5D).build();
        PaymentEntity paymentEntity = PaymentEntity.builder().id(1L).build();
        when(creditEntityManager.save(any(CreditRequest.class))).thenReturn(creditEntity);
        when(paymentEntityManager.save(any(Payment.class))).thenReturn(paymentEntity);

        List<Payment> result = interestService.calculateInterest(creditRequest);
        assertThat(result).isNotNull();
        assertThat(result.size()).isEqualTo(4);
        verify(creditEntityManager, times(1)).save(any(CreditRequest.class));
        verify(paymentEntityManager, times(4)).save(any(Payment.class));
    }

    @Test
    public void calculateInterestAmountWithException() {
        CreditRequest creditRequest = CreditRequest.builder().amount(-1D).terms(1).rate(5D).build();
        BadRequestException exception = assertThrows(BadRequestException.class, () -> interestService.calculateInterest(creditRequest));
        assertThat(exception.getCode()).isEqualTo("400#amount");
        assertThat(exception.getMessage()).isEqualTo("amount can't be lower than or equal to 0");
    }

    @Test
    public void calculateInterestTermsWithException() {
        CreditRequest creditRequest = CreditRequest.builder().amount(1000D).terms(0).rate(5D).build();
        BadRequestException exception = assertThrows(BadRequestException.class, () -> interestService.calculateInterest(creditRequest));
        assertThat(exception.getCode()).isEqualTo("400#terms");
        assertThat(exception.getMessage()).isEqualTo("terms can't be lower than or equal to 0");
    }

    @Test
    public void calculateInterestRateWithException() {
        CreditRequest creditRequest = CreditRequest.builder().amount(1000D).terms(10).rate(-1D).build();
        BadRequestException exception = assertThrows(BadRequestException.class, () -> interestService.calculateInterest(creditRequest));
        assertThat(exception.getCode()).isEqualTo("400#rate");
        assertThat(exception.getMessage()).isEqualTo("rate can't be lower than or equal to 0");
    }
}