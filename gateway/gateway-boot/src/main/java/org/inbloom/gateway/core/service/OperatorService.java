package org.inbloom.gateway.core.service;

import org.inbloom.gateway.core.event.operator.*;
import org.springframework.stereotype.Service;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
@Service
public interface OperatorService {

    public RegisteredOperatorEvent registerOperator(RegisterOperatorEvent operatorRegisterEvent);

    public RetrievedOperatorEvent retrieveOperator(RetrieveOperatorEvent retrieveOperatorEvent);

    public ModifiedOperatorEvent modifyOperator(ModifyOperatorEvent modifyOperatorEvent);

}
