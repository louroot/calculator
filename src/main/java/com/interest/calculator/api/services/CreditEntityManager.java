package com.interest.calculator.api.services;

import com.interest.calculator.api.db.CreditEntityRepository;
import com.interest.calculator.api.db.entities.CreditEntity;
import com.interest.calculator.api.interest.CreditRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CreditEntityManager {
    private final CreditEntityRepository creditEntityRepository;
    
    public CreditEntity save(CreditRequest creditRequest) {
        CreditEntity creditEntity = CreditEntity.builder().amount(creditRequest.getAmount()).rate(creditRequest.getRate()).terms(creditRequest.getTerms()).build();
        return creditEntityRepository.save(creditEntity);
    }
}
