package org.inbloom.gateway.persistence.service;


import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.core.event.GatewayRequest;
import org.inbloom.gateway.core.event.GatewayResponse;
import org.inbloom.gateway.core.event.operator.ModifiedOperatorEvent;
import org.inbloom.gateway.core.event.operator.ModifyOperatorEvent;
import org.inbloom.gateway.core.event.operator.RetrieveOperatorEvent;
import org.inbloom.gateway.core.event.operator.RetrievedOperatorEvent;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public interface OperatorPersistenceService {

    public GatewayResponse<Operator> registerOperator(GatewayRequest<Operator> registerOperatorEvent);

    RetrievedOperatorEvent retrieveOperator(RetrieveOperatorEvent retrieveOperatorEvent);

    ModifiedOperatorEvent modifyOperator(ModifyOperatorEvent modifyOperatorEvent);
}
