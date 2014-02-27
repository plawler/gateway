package com.sample.gateway.persistence.service;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.*;
import com.sample.gateway.persistence.repository.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public interface OperatorPersistenceService {

    public RegisteredOperatorEvent registerOperator(RegisterOperatorEvent registerOperatorEvent);

    RetrievedOperatorEvent retrieveOperator(RetrieveOperatorEvent retrieveOperatorEvent);

    ModifiedOperatorEvent modifyOperator(ModifyOperatorEvent modifyOperatorEvent);
}
