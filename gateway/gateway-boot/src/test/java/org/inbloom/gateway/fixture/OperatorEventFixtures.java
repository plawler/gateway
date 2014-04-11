package org.inbloom.gateway.fixture;

import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.common.status.Status;
import org.inbloom.gateway.core.event.GatewayAction;
import org.inbloom.gateway.core.event.GatewayResponse;
import org.inbloom.gateway.core.event.operator.ModifiedOperatorEvent;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class OperatorEventFixtures {

    public static GatewayResponse<Operator> operatorRegistered(Long operatorId) {
        Operator operator = OperatorFixture.buildOperator();
        operator.setOperatorId(operatorId);

        return new GatewayResponse<Operator>(GatewayAction.CREATE, operator, new GatewayStatus(Status.SUCCESS));
    }

    public static ModifiedOperatorEvent operatorModified(Long operatorId) {
        Operator operator = OperatorFixture.buildOperator();
        operator.setOperatorId(operatorId);
        return ModifiedOperatorEvent.success(operatorId, operator);
    }

    public static ModifiedOperatorEvent operatorModifiedNotFound(Long id) {
        return ModifiedOperatorEvent.notFound(id);
    }
}
