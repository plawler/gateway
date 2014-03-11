package org.inbloom.gateway.persistence.service;


import org.inbloom.gateway.core.event.*;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public interface OperatorPersistenceService {

    public RegisteredOperatorEvent registerOperator(RegisterOperatorEvent registerOperatorEvent);

    RetrievedOperatorEvent retrieveOperator(RetrieveOperatorEvent retrieveOperatorEvent);

    ModifiedOperatorEvent modifyOperator(ModifyOperatorEvent modifyOperatorEvent);
}
