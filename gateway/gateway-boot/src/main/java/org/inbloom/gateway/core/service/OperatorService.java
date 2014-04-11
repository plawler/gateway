package org.inbloom.gateway.core.service;

import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.core.event.GatewayRequest;
import org.inbloom.gateway.core.event.GatewayResponse;
import org.inbloom.gateway.core.event.operator.ModifiedOperatorEvent;
import org.inbloom.gateway.core.event.operator.ModifyOperatorEvent;
import org.springframework.stereotype.Service;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
@Service
public interface OperatorService {

    public GatewayResponse<Operator> registerOperator(GatewayRequest<Operator> operatorRegisterEvent);

    public GatewayResponse<Operator>  retrieveOperator(GatewayRequest<Operator> retrieveOperatorEvent);

    public ModifiedOperatorEvent modifyOperator(ModifyOperatorEvent modifyOperatorEvent);

}
