package org.inbloom.gateway.core.service;

import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.core.event.CreateVerificationEvent;
import org.inbloom.gateway.core.event.CreatedVerificationEvent;
import org.inbloom.gateway.fixture.VerificationEventFixtures;
import org.inbloom.gateway.persistence.service.VerificationPersistenceService;
import org.inbloom.gateway.rest.util.TestUtil;
import org.inbloom.gateway.util.keyService.KeyGenerator;
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

import static junit.framework.Assert.assertNotNull;
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
public class VerificationServiceTest {

    private MockMvc mockMvc;

    @Mock
    KeyGenerator keyGenerator;

    @Mock
    VerificationPersistenceService verificationPersistenceService;

    @InjectMocks
    VerificationService verificationService = new VerificationServiceHandler();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(verificationService)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setHandlerExceptionResolvers(TestUtil.createExceptionResolver())
                .build();
    }

    @Test
    public void testCreateVerification() {
        when(verificationPersistenceService.createVerification(any(CreateVerificationEvent.class)))
                .thenReturn(VerificationEventFixtures.buildSuccessCreatedVerificationEvent(1l, 1l));

        when(keyGenerator.generateKey()).thenReturn("XXSecretKeyXX");

        CreatedVerificationEvent createdEvent = verificationService.createVerification(VerificationEventFixtures.buildCreateVerificationEvent());

        assertNotNull(createdEvent);
        assertNotNull(createdEvent.getData());
        assertNotNull(createdEvent.getData().getToken());
        assertNotNull(createdEvent.getData().getValidFrom());
        assertNotNull(createdEvent.getData().getValidUntil());
    }


}
