package com.sample.gateway.rest.fixture;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.ModifiedOperatorEvent;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import com.sample.gateway.core.event.RetrievedOperatorEvent;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class OperatorEventFixtures {

    public static RegisteredOperatorEvent operatorRegistered(Long operatorId) {
        Operator operator = newOperator();
        operator.setOperatorId(operatorId);

        return new RegisteredOperatorEvent(operator);
    }

    public static RetrievedOperatorEvent operatorRetrieved(Long operatorId) {
        Operator operator = newOperator();
        operator.setOperatorId(operatorId);

        return new RetrievedOperatorEvent(operator);
    }

    public static RetrievedOperatorEvent operatorNotFound() {
        return new RetrievedOperatorEvent(null);
    }

    public static ModifiedOperatorEvent operatorModified(Long operatorId) {
        Operator operator = newOperator();
        operator.setOperatorId(operatorId);
        return ModifiedOperatorEvent.success(operatorId, operator);
    }

    public static ModifiedOperatorEvent operatorModifiedNotFound(Long id) {
        return ModifiedOperatorEvent.notFound(id);
    }

    public static Operator newOperator() {
        Operator operator = new Operator();
        operator.setApiUri("https://localhost/api");
        operator.setConnectorUri("https://localhost/connector");

        operator.setEnabled(true);
        operator.setOperatorName("Fixture Data Operator");

        operator.setPrimaryContactName("John Jacob Jingleheimer-Smith");
        operator.setPrimaryContactEmail("jjingleheimersmith@some-domain.com");
        operator.setPrimaryContactPhone("1234567890");


        return operator;
    }

    public static Operator registerInvalidOperator() {
        Operator operator = newOperator();
        operator.setOperatorName(null);
        operator.setApiUri("not a valid url");
        operator.setConnectorUri("much too long string dsgfafsadfsadjgkhsadkljghlaksdjghkjasdghlkjsdghkjshglkjsajlhsdklgjhasdkjghskaldjghksdajghlkasdjghkljsdghklasjdghkasdjghlkasdjghkjsadghksjdghsdagfsdadsgfdsgdsgdsgsdg");
        return operator;
    }
}
