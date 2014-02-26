package com.sample.gateway.persistence.repository;

import com.sample.gateway.persistence.domain.OperatorEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lloydengebretsen on 2/15/14.
 */
public interface OperatorRepository extends CrudRepository<OperatorEntity, Long> {

    OperatorEntity findByOperatorId(Long operatorId);

    OperatorEntity findByOperatorName(String operatorName);
}
