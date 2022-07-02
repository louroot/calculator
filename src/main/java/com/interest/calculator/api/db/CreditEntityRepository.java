package com.interest.calculator.api.db;

import com.interest.calculator.api.db.entities.CreditEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditEntityRepository extends CrudRepository<CreditEntity, Long> {
}
