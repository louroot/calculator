package com.interest.calculator.api.db;

import com.interest.calculator.api.db.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentEntityRepository extends CrudRepository<PaymentEntity, Long> {
}
