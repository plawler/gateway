package com.sample.gateway.core.domain.service;

import com.sample.gateway.core.event.ApplicationData;
import com.sample.gateway.core.event.RegisterApplicationEvent;
import com.sample.gateway.core.event.RegisteredApplicationEvent;
import com.sample.gateway.core.service.ApplicationService;

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
@ContextConfiguration({"file:gateway-core/src/main/webapp/WEB-INF/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ApplicationServiceIntegrationTest {

    @Autowired
    ApplicationService applicationService;

    @Test
    public void shouldRegisterNewApplication() {
        ApplicationData data = new ApplicationData();
        data.setApplicationName("Test Application");
        data.setAppUri("http://localhost:9000");

        RegisterApplicationEvent registerApplicationEvent = new RegisterApplicationEvent(data);
        RegisteredApplicationEvent registered = applicationService.registerNewApplication(registerApplicationEvent);

        assertNotNull(registered);
        assertNotNull(registered.getApplicationId());
        assertEquals("TestApplication123456", registered.getClientId());
        assertNotNull(registered.getSharedSecret());
    }

}
