package com.sample.gateway.core.service;

import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import com.sample.gateway.persistence.domain.Operator;
import com.sample.gateway.persistence.repository.OperatorRepository;
import com.sample.gateway.persistence.service.OperatorPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
@Service
public class OperatorServiceHandler implements OperatorService {

    @Autowired
    private OperatorPersistenceService operatorPersistenceService;

    @Override
    public RegisteredOperatorEvent registerOperator(RegisterOperatorEvent operatorRegisterEvent) {
       return operatorPersistenceService.registerOperator(operatorRegisterEvent);
    }

}
