package com.interest.calculator.api.interest;

import com.interest.calculator.api.db.CreditEntityRepository;
import com.interest.calculator.api.db.PaymentEntityRepository;
import com.interest.calculator.api.health.HealthService;
import com.interest.calculator.api.services.CreditEntityManager;
import com.interest.calculator.api.services.PaymentEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.sql.DataSource;

public class BaseControllerTest {
    @MockBean
    public InterestService interestService;
    @MockBean
    public HealthService healthService;
    @MockBean
    public CreditEntityManager creditEntityManager;
    @MockBean
    public PaymentEntityManager paymentEntityManager;
    @MockBean
    public DataSource dataSource;
    @MockBean
    public CreditEntityRepository creditEntityRepository;
    @MockBean
    public PaymentEntityRepository paymentEntityRepository;
}
