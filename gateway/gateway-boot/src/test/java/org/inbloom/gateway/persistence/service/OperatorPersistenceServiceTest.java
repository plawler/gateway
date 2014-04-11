package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.common.status.Status;
import org.inbloom.gateway.core.event.GatewayAction;
import org.inbloom.gateway.core.event.GatewayRequest;
import org.inbloom.gateway.core.event.GatewayResponse;
import org.inbloom.gateway.core.event.operator.*;
import org.inbloom.gateway.fixture.OperatorFixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/25/14
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Gateway.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OperatorPersistenceServiceTest {

    @Autowired
    private OperatorPersistenceService operatorPersistenceService;

    @Test
    public void shouldRegisterAnOperator() {
        String operatorName = "Illini Cloud";
        GatewayResponse<Operator> event = operatorPersistenceService.registerOperator(new GatewayRequest<Operator>(GatewayAction.CREATE, OperatorFixture.buildOperator(operatorName)));

        assertNotNull(event);
        assertNotNull(event.getPayload().getOperatorId());
    }

    @Test
    public void shouldRetrieveAnOperator() {
        String operatorName = "My Operator";
        GatewayResponse<Operator> registeredEvent = operatorPersistenceService.registerOperator(new GatewayRequest<Operator>(GatewayAction.CREATE, OperatorFixture.buildOperator(operatorName)));

        assertNotNull(registeredEvent);
        assertNotNull(registeredEvent.getPayload().getOperatorId());

        Long id = registeredEvent.getPayload().getOperatorId();
        RetrievedOperatorEvent retrievedEvent = operatorPersistenceService.retrieveOperator(new RetrieveOperatorEvent(id));

        assertNotNull(retrievedEvent);
        assertEquals(id, retrievedEvent.getData().getOperatorId());
        assertEquals(operatorName, retrievedEvent.getData().getOperatorName());

    }

    @Test
    public void shouldModifyAnOperator() {
        String operatorName = "My Operator";
        String modifiedName = "Your Operator";


        GatewayResponse<Operator> registeredEvent = operatorPersistenceService.registerOperator(new GatewayRequest<Operator>(GatewayAction.CREATE, OperatorFixture.buildOperator(operatorName)));

        assertNotNull(registeredEvent);
        assertNotNull(registeredEvent.getPayload().getOperatorId());

        Long id = registeredEvent.getPayload().getOperatorId();
        Long modifiedId = new Long(id + 1);
        Operator retrievedOperator = operatorPersistenceService.retrieveOperator(new RetrieveOperatorEvent(id)).getData();
        assertEquals(operatorName, retrievedOperator.getOperatorName());

        retrievedOperator.setOperatorName(modifiedName);
        retrievedOperator.setOperatorId(modifiedId); //This should not persist to the database

        ModifiedOperatorEvent modifiedEvent = operatorPersistenceService.modifyOperator(new ModifyOperatorEvent(id, retrievedOperator));
        assertEquals(Status.SUCCESS, modifiedEvent.statusCode());



        Operator modified = operatorPersistenceService.retrieveOperator(new RetrieveOperatorEvent(id)).getData();
        assertEquals(modifiedName, modified.getOperatorName());
        assertNotEquals(modifiedId, modified.getOperatorId());
    }
}
