package org.inbloom.gateway.fixture;

import org.inbloom.gateway.core.domain.Operator;
import org.inbloom.gateway.core.event.ModifiedOperatorEvent;
import org.inbloom.gateway.core.event.RegisteredOperatorEvent;
import org.inbloom.gateway.core.event.RetrievedOperatorEvent;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class OperatorEventFixtures {

    public static RegisteredOperatorEvent operatorRegistered(Long operatorId) {
        Operator operator = OperatorFixture.buildOperator();
        operator.setOperatorId(operatorId);

        return RegisteredOperatorEvent.success(operator);
    }

    public static RetrievedOperatorEvent operatorRetrieved(Long operatorId) {
        Operator operator = OperatorFixture.buildOperator();
        operator.setOperatorId(operatorId);

        return RetrievedOperatorEvent.success(operator);
    }

    public static RetrievedOperatorEvent operatorNotFound() {
        return RetrievedOperatorEvent.notFound();
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