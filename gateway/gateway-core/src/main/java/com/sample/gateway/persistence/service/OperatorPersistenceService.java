package com.sample.gateway.persistence.service;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import com.sample.gateway.persistence.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public interface OperatorPersistenceService {

    public RegisteredOperatorEvent registerOperator(RegisterOperatorEvent registerOperatorEvent);
}
