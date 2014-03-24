package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.core.event.RegisterApplicationProviderEvent;
import org.inbloom.gateway.core.event.RegisteredApplicationProviderEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.fixture.ApplicationProviderEventFixtures;
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
        RegisterApplicationProviderEvent request = ApplicationProviderEventFixtures.buildRegisterApplicationProviderEvent();
        RegisteredApplicationProviderEvent response = applicationProviderPersistenceService.createApplicationProvider(request);

        assertEquals(ResponseEvent.Status.SUCCESS, response.status());
        assertEquals(request.getData().getApplicationProviderName(), response.getData().getApplicationProviderName());
        assertEquals(request.getData().getOrganizationName(), response.getData().getOrganizationName());
        assertNotNull(response.getData().getApplicationProviderId());

        //test cascade for user entity
        assertEquals(request.getData().getUser().getEmail(), response.getData().getUser().getEmail());
    }
}
