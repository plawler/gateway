package org.inbloom.gateway.core.service;

import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.persistence.service.OperatorPersistenceService;
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
        if(retrievedOperatorEvent.status() == ResponseEvent.Status.NOT_FOUND) {
             //could not find entity by id
            return ModifiedOperatorEvent.notFound(modifyOperatorEvent.getId());
        }

        return operatorPersistenceService.modifyOperator(modifyOperatorEvent);
    }

}
