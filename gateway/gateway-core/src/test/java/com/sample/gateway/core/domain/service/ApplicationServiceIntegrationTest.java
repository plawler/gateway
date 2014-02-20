package com.sample.gateway.core.domain.service;

import com.sample.gateway.core.domain.ApplicationProvider;
import com.sample.gateway.core.event.*;
import com.sample.gateway.core.service.ApplicationProviderService;
import com.sample.gateway.core.service.ApplicationService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/17/14
 * Time: 9:02 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ApplicationServiceIntegrationTest {

    @Autowired
    ApplicationService applicationService;

    @Autowired
    ApplicationProviderService applicationProviderService;

    private ApplicationProvider provider;

    @Before
    public void setup() {
        ApplicationProviderData data = new ApplicationProviderData();
        data.setApplicationProviderName("Bob the Developer");
        data.setUsername("bdeveloper");
        data.setTermsAccepted(true);
        RegisteredApplicationProviderEvent event = applicationProviderService.registerApplicationProvider(new RegisterApplicationProviderEvent(data));
        provider = ApplicationProvider.fromApplicationProviderData(event.getData());
    }

    @Test
    public void shouldRegisterNewApplication() {
        ApplicationData data = new ApplicationData();
        data.setApplicationProviderId(provider.getApplicationProviderId());
        data.setApplicationName("Test Application");
        data.setAppUri("http://localhost:9000");

        RegisterApplicationEvent registerApplicationEvent = new RegisterApplicationEvent(data);
        RegisteredApplicationEvent registered = applicationService.registerNewApplication(registerApplicationEvent);

        assertNotNull(registered);
        assertNotNull(registered.getApplicationId());
        assertEquals(provider.getApplicationProviderId(), registered.getApplicationProviderId());
        assertTrue(registered.getApproved());
        assertNotNull(registered.getClientId());
        assertNotNull(registered.getSharedSecret());
    }

    @Test
    public void shouldModifyAnApplication() {
        final String modifiedApplicationUri = "http://localhost:9000/modifiedtestapp";

        ApplicationData data = new ApplicationData();
        data.setApplicationProviderId(provider.getApplicationProviderId());
        data.setApplicationName("Test Application");
        data.setDescription("An application used as a fixture for testing");
        data.setAppUri("http://localhost:9000/testapp");

        RegisteredApplicationEvent registered = applicationService.registerNewApplication(new RegisterApplicationEvent(data));
        RetrievedApplicationEvent retrieved = applicationService.retrieveApplication(new RetrieveApplicationEvent(registered.getApplicationId()));

        ApplicationData retrievedData = retrieved.getData();
        retrievedData.setAppUri(modifiedApplicationUri);

        ModifiedApplicationEvent modified = applicationService.modifyApplication(new ModifyApplicationEvent(retrievedData));
        RetrievedApplicationEvent retrievedAgain = applicationService.retrieveApplication(new RetrieveApplicationEvent(modified.getApplicationId()));

        assertEquals(modifiedApplicationUri, retrievedAgain.getData().getAppUri());
    }

}
