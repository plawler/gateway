package org.inbloom.gateway.core.service;

import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.common.status.Status;
import org.inbloom.gateway.core.event.GatewayAction;
import org.inbloom.gateway.core.event.GatewayRequest;
import org.inbloom.gateway.core.event.GatewayResponse;
import org.inbloom.gateway.core.event.operator.ModifiedOperatorEvent;
import org.inbloom.gateway.core.event.operator.ModifyOperatorEvent;
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
    public GatewayResponse<Operator> registerOperator(GatewayRequest<Operator> registerOperatorEvent) {
       return operatorPersistenceService.registerOperator(registerOperatorEvent);
    }

    @Override
    @Transactional(readOnly = true)
    public GatewayResponse<Operator> retrieveOperator(GatewayRequest<Operator> retrieveOperatorEvent) {
        return operatorPersistenceService.retrieveOperator(retrieveOperatorEvent);
    }

    @Override
    public ModifiedOperatorEvent modifyOperator(ModifyOperatorEvent modifyOperatorEvent) {

        Operator operator = new Operator();
        operator.setOperatorId(modifyOperatorEvent.getId());
        GatewayResponse<Operator> retrievedOperatorEvent = retrieveOperator(new GatewayRequest<Operator>(GatewayAction.RETRIEVE, operator));
        if(retrievedOperatorEvent.getStatus().getStatus() == Status.NOT_FOUND) {
             //could not find entity by id
            return ModifiedOperatorEvent.notFound(modifyOperatorEvent.getId());
        }

        return operatorPersistenceService.modifyOperator(modifyOperatorEvent);
    }

}
