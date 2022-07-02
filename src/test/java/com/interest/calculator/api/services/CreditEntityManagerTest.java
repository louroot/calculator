package com.interest.calculator.api.services;

import com.interest.calculator.api.db.CreditEntityRepository;
import com.interest.calculator.api.db.entities.CreditEntity;
import com.interest.calculator.api.interest.CreditRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class CreditEntityManagerTest {
    @Mock
    CreditEntityRepository creditEntityRepository;

    @InjectMocks
    CreditEntityManager creditEntityManager;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    public void save() {
        when(creditEntityRepository.save(any(CreditEntity.class))).thenReturn(CreditEntity.builder().build());
        CreditEntity saved = creditEntityManager.save(CreditRequest.builder().amount(10D).rate(5D).terms(12).build());
        assertThat(saved).isNotNull();
        verify(creditEntityRepository, times(1)).save(any(CreditEntity.class));
    }
}