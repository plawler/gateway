package com.sample.gateway.core.service;

import com.sample.gateway.core.event.*;
import com.sample.gateway.persistence.service.OperatorPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
@Service
@Transactional
public class OperatorServiceHandler implements OperatorService {

    @Autowired
    private OperatorPersistenceService operatorPersistenceService;

    @Override
    public RegisteredOperatorEvent registerOperator(RegisterOperatorEvent registerOperatorEvent) {
       return operatorPersistenceService.registerOperator(registerOperatorEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public RetrievedOperatorEvent retrieveOperator(RetrieveOperatorEvent retrieveOperatorEvent) {
        return operatorPersistenceService.retrieveOperator(retrieveOperatorEvent);
    }

    @Override
    public ModifiedOperatorEvent modifyOperator(ModifyOperatorEvent modifyOperatorEvent) {

        RetrievedOperatorEvent retrievedOperatorEvent = retrieveOperator(new RetrieveOperatorEvent(modifyOperatorEvent.getId()));
        if(retrievedOperatorEvent.getData() == null)
        {
            //could not find entity by id
            return ModifiedOperatorEvent.notFound(modifyOperatorEvent.getId());
        }

        return operatorPersistenceService.modifyOperator(modifyOperatorEvent);
    }

}
