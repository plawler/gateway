package com.sample.gateway.persistence.repository;

import com.sample.gateway.persistence.domain.Operator;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lloydengebretsen on 2/15/14.
 */
public interface OperatorRepository extends CrudRepository<Operator, Long> {

    Operator findByOperatorId(Long operatorId);

    Operator findByOperatorName(String operatorName);
}
