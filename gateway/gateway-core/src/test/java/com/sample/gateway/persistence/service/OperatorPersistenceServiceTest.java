package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.*;
import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.persistence.domain.fixture.Fixture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
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
@ContextConfiguration({"classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OperatorPersistenceServiceTest {

    @Autowired
    private OperatorPersistenceService operatorPersistenceService;

    @Test
    public void shouldRegisterAnOperator() {
        String operatorName = "Illini Cloud";
        RegisteredOperatorEvent event = operatorPersistenceService.registerOperator(new RegisterOperatorEvent(Fixture.buildOperator(operatorName)));

        assertNotNull(event);
        assertNotNull(event.getOperatorId());
    }

    @Test
    public void shouldRetrieveAnOperator() {
        String operatorName = "My Operator";
        RegisteredOperatorEvent registeredEvent = operatorPersistenceService.registerOperator(new RegisterOperatorEvent(Fixture.buildOperator(operatorName)));

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


        RegisteredOperatorEvent registeredEvent = operatorPersistenceService.registerOperator(new RegisterOperatorEvent(Fixture.buildOperator(operatorName)));

        assertNotNull(registeredEvent);
        assertNotNull(registeredEvent.getOperatorId());

        Long id = registeredEvent.getOperatorId();
        Long modifiedId = new Long(id + 1);
        Operator retrievedOperator = operatorPersistenceService.retrieveOperator(new RetrieveOperatorEvent(id)).getData();
        assertEquals(operatorName, retrievedOperator.getOperatorName());

        retrievedOperator.setOperatorName(modifiedName);
        retrievedOperator.setOperatorId(modifiedId); //This should not persist to the database

        ModifiedOperatorEvent modifiedEvent = operatorPersistenceService.modifyOperator(new ModifyOperatorEvent(id, retrievedOperator));
        assertTrue(modifiedEvent.isUpdateSuccessful());



        Operator modified = operatorPersistenceService.retrieveOperator(new RetrieveOperatorEvent(id)).getData();
        assertEquals(modifiedName, modified.getOperatorName());
        assertNotEquals(modifiedId, modified.getOperatorId());
    }
}
