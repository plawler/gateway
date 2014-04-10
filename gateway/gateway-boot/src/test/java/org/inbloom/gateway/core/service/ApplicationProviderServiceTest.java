package org.inbloom.gateway.core.service;

import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.core.event.verification.CreateVerificationEvent;
import org.inbloom.gateway.core.event.provider.RegisterApplicationProviderEvent;
import org.inbloom.gateway.core.event.provider.RegisteredApplicationProviderEvent;
import org.inbloom.gateway.common.status.ApplicationProviderStatus;
import org.inbloom.gateway.fixture.ApplicationProviderEventFixtures;
import org.inbloom.gateway.fixture.VerificationEventFixtures;
import org.inbloom.gateway.persistence.domain.UserEntity;
import org.inbloom.gateway.persistence.service.ApplicationProviderPersistenceService;
import org.inbloom.gateway.rest.util.TestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * @author benjaminmorgan
 *         Date: 3/27/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Gateway.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ApplicationProviderServiceTest {

    private MockMvc mockMvc;

    @Mock
    ApplicationProviderPersistenceService appProviderPersistenceService;

    @Mock
    VerificationService verificationService;

    @InjectMocks
    ApplicationProviderService appProviderService = new ApplicationProviderServiceHandler();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(appProviderService)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setHandlerExceptionResolvers(TestUtil.createExceptionResolver())
                .build();
    }


    @Test
    public void testRegisterAppProvider()
    {
        when(appProviderPersistenceService.createApplicationProvider(any(RegisterApplicationProviderEvent.class)))
                .thenReturn(ApplicationProviderEventFixtures.buildSuccessRegisteredAppProviderEvent(1l));

        when(appProviderPersistenceService.getUserByEmail(any(String.class))).thenReturn(null);

        when(verificationService.createVerification(any(CreateVerificationEvent.class)))
                .thenReturn(VerificationEventFixtures.buildSuccessCreatedVerificationEvent(1l, 1l));

        RegisteredApplicationProviderEvent registeredEvent = appProviderService.registerApplicationProvider(ApplicationProviderEventFixtures.buildRegisterAppProviderEvent());

        assertNotNull(registeredEvent);
        assertEquals(registeredEvent.status(), ApplicationProviderStatus.SUCCESS);
    }

    @Test
    public void testRegisterDuplicateAppProvider()
    {
        when(appProviderPersistenceService.getUserByEmail(any(String.class))).thenReturn(new UserEntity());

        RegisteredApplicationProviderEvent registeredEvent = appProviderService.registerApplicationProvider(ApplicationProviderEventFixtures.buildRegisterAppProviderEvent());

        assertNotNull(registeredEvent);
        assertEquals(registeredEvent.status(), ApplicationProviderStatus.ACCOUNT_EXISTS);
    }

}
