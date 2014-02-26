package com.sample.gateway.rest.fixture;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import com.sample.gateway.core.event.RetrievedOperatorEvent;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class OperatorEventFixtures {

    public static RegisteredOperatorEvent operatorRegistered(Long operatorId) {
        Operator operator = registerOperator();
        operator.setOperatorId(operatorId);

        return new RegisteredOperatorEvent(operator);
    }

    public static RetrievedOperatorEvent operatorRetrieved(Long operatorId) {
        Operator operator = registerOperator();
        operator.setOperatorId(operatorId);

        return new RetrievedOperatorEvent(operator);
    }

    public static Operator registerOperator() {
        Operator operator = new Operator();
        operator.setOperatorId(1L);
        operator.setApiUri("https://localhost/api");
        operator.setConnectorUri("https://localhost/connector");

        operator.setContractStartOn(new Date());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        operator.setContractEndOn(cal.getTime());

        operator.setEnabled(true);
        operator.setOperatorName("Fixture Data Operator");

        return operator;
    }
}
