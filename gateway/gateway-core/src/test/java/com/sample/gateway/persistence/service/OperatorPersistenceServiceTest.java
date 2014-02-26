package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import com.sample.gateway.core.event.RetrieveOperatorEvent;
import com.sample.gateway.core.event.RetrievedOperatorEvent;
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
        RegisteredOperatorEvent event = createOperator("Illini Cloud");

        assertNotNull(event);
        assertNotNull(event.getOperatorId());
    }

    @Test
    public void shouldRetrieveAnOperator() {
        String operatorName = "My Operator";
        RegisteredOperatorEvent registeredEvent = createOperator(operatorName);

        assertNotNull(registeredEvent);
        assertNotNull(registeredEvent.getOperatorId());

        Long id = registeredEvent.getOperatorId();
        RetrievedOperatorEvent retrievedEvent = retrieveOperator(id);

        assertNotNull(retrievedEvent);
        assertEquals(id, retrievedEvent.getData().getOperatorId());
        assertEquals(operatorName, retrievedEvent.getData().getOperatorName());

    }

    private RetrievedOperatorEvent retrieveOperator(Long id) {
        Operator template = new Operator();
        template.setOperatorId(id);
        return operatorPersistenceService.retrieveOperator(new RetrieveOperatorEvent(template));
    }

    private RegisteredOperatorEvent createOperator(String name) {
        Operator operator = new Operator();
        operator.setOperatorName(name);
        operator.setEnabled(true);

        return operatorPersistenceService.registerOperator(new RegisterOperatorEvent(operator));
    }

}
