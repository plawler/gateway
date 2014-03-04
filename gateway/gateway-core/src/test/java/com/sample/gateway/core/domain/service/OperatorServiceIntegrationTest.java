package com.sample.gateway.core.domain.service;

import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
import com.sample.gateway.core.event.RetrieveOperatorEvent;
import com.sample.gateway.core.event.RetrievedOperatorEvent;
import com.sample.gateway.core.service.OperatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static com.sample.gateway.rest.fixture.OperatorEventFixtures.newOperator;
import static org.junit.Assert.*;

/**
 * Created By: paullawler
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OperatorServiceIntegrationTest {

    @Autowired
    private OperatorService operatorService;

    @Test
    public void shouldRegisterAnOperatorWithATransaction() {
        Operator operator = newOperator();
        RegisteredOperatorEvent registered = operatorService.registerOperator(new RegisterOperatorEvent(operator));
        RetrievedOperatorEvent retrieved = operatorService.retrieveOperator(new RetrieveOperatorEvent(registered.getOperatorId()));
        assertEquals(retrieved.getData().getOperatorId(), registered.getOperatorId());
    }

}
