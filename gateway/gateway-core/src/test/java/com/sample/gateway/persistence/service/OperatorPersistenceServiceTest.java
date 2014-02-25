package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.RegisterOperatorEvent;
import com.sample.gateway.core.domain.Operator;
import com.sample.gateway.core.event.RegisteredOperatorEvent;
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
        Operator operator = new Operator();
        operator.setOperatorName("Illini Cloud");
        operator.setEnabled(true);

        RegisteredOperatorEvent event = operatorPersistenceService.registerOperator(new RegisterOperatorEvent(operator));

        assertNotNull(event);
        assertNotNull(event.getOperatorId());
    }

}
