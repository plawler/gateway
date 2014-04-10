package org.inbloom.gateway.core.service;

/**
 * Created By: paullawler
 */

import org.inbloom.gateway.credentials.CredentialService;
import org.junit.runner.RunWith;

import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.core.event.verification.CreateVerificationEvent;
import org.inbloom.gateway.core.event.verification.CreatedVerificationEvent;
import org.inbloom.gateway.fixture.VerificationEventFixtures;
import org.inbloom.gateway.persistence.service.VerificationPersistenceService;
import org.inbloom.gateway.rest.util.TestUtil;
import org.inbloom.gateway.util.keyService.KeyGenerator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.jvnet.mock_javamail.Mailbox;
import javax.mail.Message;
import javax.mail.internet.AddressException;


import java.util.List;

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
public class VerificationServiceIntegrationTest {

    private MockMvc mockMvc;

    @Mock
    KeyGenerator keyGenerator;

    @Mock
    VerificationPersistenceService persistenceService;

    @Mock
    CredentialService credentialService;

    @Mock
    Environment env;

    VerificationService verificationService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        verificationService = new VerificationServiceHandler(persistenceService, credentialService, keyGenerator, env);

        this.mockMvc = MockMvcBuilders.standaloneSetup(verificationService)
                .setMessageConverters(new MappingJackson2HttpMessageConverter())
                .setHandlerExceptionResolvers(TestUtil.createExceptionResolver())
                .build();
    }

    @Test
    public void testCreateVerification() throws AddressException {

        Mailbox.clearAll();

        when(persistenceService.createVerification(any(CreateVerificationEvent.class)))
                .thenReturn(VerificationEventFixtures.buildSuccessCreatedVerificationEvent(1l, 1l));

        when(keyGenerator.generateKey()).thenReturn("XXSecretKeyXX");

        CreatedVerificationEvent createdEvent = verificationService.createVerification(VerificationEventFixtures.buildCreateVerificationEvent());

        assertNotNull(createdEvent);
        assertNotNull(createdEvent.getData());
        assertNotNull(createdEvent.getData().getToken());
        assertNotNull(createdEvent.getData().getValidFrom());
        assertNotNull(createdEvent.getData().getValidUntil());

        //check that in-memory email server received email
        List<Message> inbox = Mailbox.get(createdEvent.getData().getUser().getEmail());
        assertEquals(inbox.size(), 1);
    }


}
