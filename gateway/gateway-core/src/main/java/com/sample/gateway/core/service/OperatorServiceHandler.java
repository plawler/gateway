package com.sample.gateway.core.service;

import com.sample.gateway.core.event.*;
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
    public RegisteredOperatorEvent registerOperator(RegisterOperatorEvent registerOperatorEvent) {
       return operatorPersistenceService.registerOperator(registerOperatorEvent);
    }

    @Override
    public RetrievedOperatorEvent retrieveOperator(RetrieveOperatorEvent retrieveOperatorEvent) {
        return operatorPersistenceService.retrieveOperator(retrieveOperatorEvent);
    }

    @Override
    public ModifiedOperatorEvent modifyOperator(ModifyOperatorEvent modifyOperatorEvent) {
        return operatorPersistenceService.modifyOperator(modifyOperatorEvent);
    }

}
