package org.inbloom.gateway.core.service;

import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.core.event.operator.RegisterOperatorEvent;
import org.inbloom.gateway.core.event.operator.RegisteredOperatorEvent;
import org.inbloom.gateway.core.event.operator.RetrieveOperatorEvent;
import org.inbloom.gateway.core.event.operator.RetrievedOperatorEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.inbloom.gateway.fixture.OperatorFixture.buildOperator;
import static org.junit.Assert.assertEquals;

/**
 * Created By: paullawler
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Gateway.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OperatorServiceIntegrationTest {

    @Autowired
    private OperatorService operatorService;

    @Test
    public void shouldRegisterAnOperatorWithATransaction() {
        Operator operator = buildOperator();
        RegisteredOperatorEvent registered = operatorService.registerOperator(new RegisterOperatorEvent(operator));
        RetrievedOperatorEvent retrieved = operatorService.retrieveOperator(new RetrieveOperatorEvent(registered.getOperatorId()));
        assertEquals(retrieved.getData().getOperatorId(), registered.getOperatorId());
    }

}
