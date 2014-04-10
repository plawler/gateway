package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.core.domain.Operator;
import org.inbloom.gateway.core.event.operator.*;
import org.inbloom.gateway.common.status.OperatorStatus;
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
        RegisteredOperatorEvent event = operatorPersistenceService.registerOperator(new RegisterOperatorEvent(OperatorFixture.buildOperator(operatorName)));

        assertNotNull(event);
        assertNotNull(event.getOperatorId());
    }

    @Test
    public void shouldRetrieveAnOperator() {
        String operatorName = "My Operator";
        RegisteredOperatorEvent registeredEvent = operatorPersistenceService.registerOperator(new RegisterOperatorEvent(OperatorFixture.buildOperator(operatorName)));

        assertNotNull(registeredEvent);
        assertNotNull(registeredEvent.getOperatorId());

        Long id = registeredEvent.getOperatorId();
        RetrievedOperatorEvent retrievedEvent = operatorPersistenceService.retrieveOperator(new RetrieveOperatorEvent(id));

        assertNotNull(retrievedEvent);
        assertEquals(id, retrievedEvent.getData().getOperatorId());
        assertEquals(operatorName, retrievedEvent.getData().getOperatorName());

    }

    @Test
    public void shouldModifyAnOperator() {
        String operatorName = "My Operator";
        String modifiedName = "Your Operator";


        RegisteredOperatorEvent registeredEvent = operatorPersistenceService.registerOperator(new RegisterOperatorEvent(OperatorFixture.buildOperator(operatorName)));

        assertNotNull(registeredEvent);
        assertNotNull(registeredEvent.getOperatorId());

        Long id = registeredEvent.getOperatorId();
        Long modifiedId = new Long(id + 1);
        Operator retrievedOperator = operatorPersistenceService.retrieveOperator(new RetrieveOperatorEvent(id)).getData();
        assertEquals(operatorName, retrievedOperator.getOperatorName());

        retrievedOperator.setOperatorName(modifiedName);
        retrievedOperator.setOperatorId(modifiedId); //This should not persist to the database

        ModifiedOperatorEvent modifiedEvent = operatorPersistenceService.modifyOperator(new ModifyOperatorEvent(id, retrievedOperator));
        assertEquals(OperatorStatus.SUCCESS, modifiedEvent.status());



        Operator modified = operatorPersistenceService.retrieveOperator(new RetrieveOperatorEvent(id)).getData();
        assertEquals(modifiedName, modified.getOperatorName());
        assertNotEquals(modifiedId, modified.getOperatorId());
    }
}
