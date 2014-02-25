package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import com.sample.gateway.persistence.domain.Operator;
import com.sample.gateway.persistence.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
@Service
public class OperatorPersistenceHandler implements OperatorPersistenceService{

    @Autowired
    private OperatorRepository operatorRepository;

    @Override
    public RegisteredOperatorEvent registerOperator(RegisterOperatorEvent registerOperatorEvent) {
        Operator operator = Operator.fromDomain(registerOperatorEvent.getData());
        operatorRepository.save(operator);
        RegisteredOperatorEvent registeredEvent = new RegisteredOperatorEvent(operator.toDomain());
        return registeredEvent;
    }
}
