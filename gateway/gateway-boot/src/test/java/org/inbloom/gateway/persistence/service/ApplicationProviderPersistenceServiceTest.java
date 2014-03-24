package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.Gateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Gateway.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ApplicationProviderPersistenceServiceTest {

    @Autowired
    ApplicationProviderPersistenceService applicationProviderPersistenceService;

    @Test
    public void testCreateApplicationProvider(){

    }
}
