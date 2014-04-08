package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.core.event.*;
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
import static org.junit.Assert.assertEquals;

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
    public void shouldCreateAnApplicationProvider(){
        RegisterApplicationProviderEvent request = ApplicationProviderEventFixtures.buildRegisterAppProviderEvent();
        RegisteredApplicationProviderEvent response = applicationProviderPersistenceService.createApplicationProvider(request);

        assertEquals(ResponseEvent.Status.SUCCESS, response.status());
        assertEquals(request.getData().getApplicationProviderName(), response.getData().getApplicationProviderName());
        assertEquals(request.getData().getOrganizationName(), response.getData().getOrganizationName());
        assertNotNull(response.getData().getApplicationProviderId());

        //test cascade for user entity
        assertEquals(request.getData().getUser().getEmail(), response.getData().getUser().getEmail());
    }

    @Test
    public void shouldRetrieveAnApplicationProvider(){
        RegisterApplicationProviderEvent registerRequest = ApplicationProviderEventFixtures.buildRegisterAppProviderEvent();
        RegisteredApplicationProviderEvent registerResponse = applicationProviderPersistenceService.createApplicationProvider(registerRequest);
        Long appProviderId = registerResponse.getData().getApplicationProviderId();


        RetrievedApplicationProviderEvent retrieveResponse = applicationProviderPersistenceService.retrieveApplicationProvider(new RetrieveApplicationProviderEvent(appProviderId));
        assertEquals(ResponseEvent.Status.SUCCESS, retrieveResponse.status());
        assertEquals(registerRequest.getData().getOrganizationName(),retrieveResponse.getData().getOrganizationName());
        assertEquals(registerRequest.getData().getApplicationProviderName(), retrieveResponse.getData().getApplicationProviderName());

        assertNotNull(retrieveResponse.getData().getUser());
        assertEquals(registerRequest.getData().getUser().getEmail(), retrieveResponse.getData().getUser().getEmail());
    }

    @Test
    public void shouldModifyAnApplicationProvider(){
        RegisterApplicationProviderEvent registerRequest = ApplicationProviderEventFixtures.buildRegisterAppProviderEvent();
        RegisteredApplicationProviderEvent registerResponse = applicationProviderPersistenceService.createApplicationProvider(registerRequest);
        Long appProviderId = registerResponse.getData().getApplicationProviderId();

        ModifyApplicationProviderEvent modifyRequest = ApplicationProviderEventFixtures.buildModifyAppProviderEvent(appProviderId);
        ModifiedApplicationProviderEvent modifyResponse = applicationProviderPersistenceService.modifyApplicationProvider(modifyRequest);

        assertEquals(ResponseEvent.Status.SUCCESS, modifyResponse.status());

        RetrievedApplicationProviderEvent retrieveResponse = applicationProviderPersistenceService.retrieveApplicationProvider(new RetrieveApplicationProviderEvent(appProviderId));
        assertEquals(ResponseEvent.Status.SUCCESS, retrieveResponse.status());
        assertEquals(modifyRequest.getOrganizationName(),retrieveResponse.getData().getOrganizationName());
        assertEquals(modifyRequest.getApplicationProviderName(), retrieveResponse.getData().getApplicationProviderName());

        assertEquals(modifyRequest.getUserEmail(), retrieveResponse.getData().getUser().getEmail());
        assertEquals(modifyRequest.getUserLastName(), retrieveResponse.getData().getUser().getLastName());

    }
}
