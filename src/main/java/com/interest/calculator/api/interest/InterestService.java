package com.interest.calculator.api.interest;

import com.interest.calculator.api.db.entities.CreditEntity;
import com.interest.calculator.api.db.entities.PaymentEntity;
import com.interest.calculator.api.exceptions.BadRequestException;
import com.interest.calculator.api.services.CreditEntityManager;
import com.interest.calculator.api.services.PaymentEntityManager;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InterestService {
    private static final Logger logger = LoggerFactory.getLogger(InterestService.class);
    private final CreditEntityManager creditEntityManager;
    private final PaymentEntityManager paymentEntityManager;
    
    public List<Payment> calculateInterest(CreditRequest creditRequest) throws BadRequestException {
        validateCreditRequest(creditRequest);
        CreditEntity savedCreditEntity = creditEntityManager.save(creditRequest);
        logger.info(String.format("Saved credit request with id: %s", savedCreditEntity.getId()));
        return calculatePayments(creditRequest);
    }
    
    private void validateCreditRequest(CreditRequest creditRequest) throws BadRequestException {
        if (creditRequest.getAmount().equals(0D)) {
            throw new BadRequestException("400#amount", "amount can't be 0");
        }

        if (creditRequest.getTerms().equals(0)) {
            throw new BadRequestException("400#terms", "terms can't be 0");
        }

        if (creditRequest.getRate().equals(0D)) {
            throw new BadRequestException("400#rate", "rate can't be 0");
        }

    }
    
    private List<Payment> calculatePayments(CreditRequest creditRequest) {
        //simple interest formula is SI =(P X R X T) / 100
        //where SI = simple interest
        //P = principal
        //R = interest rate
        //T = time duration lets assume we get months
        Double term = creditRequest.getTerms().doubleValue() / 12;
        Double simpleInterest = (creditRequest.getAmount() * (creditRequest.getRate()/100) * term);
        DateTime currentPlusWeek = new DateTime();
        List<Date> paydayList = new ArrayList<>();
        double weeksToPay = (creditRequest.getTerms().doubleValue() * 52) / 12;
        for (int i = 1; i <= weeksToPay; i++) {
            currentPlusWeek = currentPlusWeek.plusWeeks(1);
            paydayList.add(currentPlusWeek.toDate());
        }

        int numberOfPaydays = paydayList.size();
        double singlePayment = (creditRequest.getAmount() + simpleInterest) / numberOfPaydays;
        List<Payment> paymentList = new ArrayList<>();
        for(int i = 0; i < numberOfPaydays; i++) {
            Payment payment = Payment.builder()
                .amount(singlePayment)
                .date(paydayList.get(i))
                .paymentNumber(i)
                .build();
            PaymentEntity paymentSaved = paymentEntityManager.save(payment);
            logger.info(String.format("Saved payment with id: %s", paymentSaved.getId()));
            paymentList.add(payment);
        }
        return paymentList;
    }
}
