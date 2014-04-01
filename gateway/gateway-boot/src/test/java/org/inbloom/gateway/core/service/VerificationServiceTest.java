package org.inbloom.gateway.core.service;


import org.inbloom.gateway.core.domain.AccountValidation;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.credentials.CredentialService;
import org.inbloom.gateway.fixture.VerificationFixture;
import org.inbloom.gateway.persistence.service.VerificationPersistenceService;
import org.inbloom.gateway.util.keyService.KeyGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;

/**
 * Created By: paullawler
 */
@RunWith(MockitoJUnitRunner.class)
public class VerificationServiceTest {

    @Mock
    private VerificationPersistenceService persistence;

    @Mock
    private CredentialService credentialer;

    @Mock
    private KeyGenerator keyGenerator;

    @Mock
    private Environment env;

    private VerificationServiceHandler verificationService;

    @Before
    public void setUp() {
        verificationService = new VerificationServiceHandler(persistence, credentialer, keyGenerator, env);
    }

    @Test
    public void shouldFailIfVerificationHasExpired() {
        ValidateAccountSetupEvent validate = new ValidateAccountSetupEvent(accountValidation());

        Mockito.when(persistence.retrieveForAccountValidation(validate))
                .thenReturn(expiredVerificationEvent(validate.getValidationDate()));

        ValidatedAccountSetupEvent validated = verificationService.validateAccountSetup(validate);
        assertEquals(ResponseEvent.Status.FAILED, validated.status());
    }

    @Test
    public void shouldBootstrapValidatedAccount() {
        ValidateAccountSetupEvent validate = new ValidateAccountSetupEvent(accountValidation());

        Mockito.when(persistence.retrieveForAccountValidation(validate))
                .thenReturn(validVerificationEvent(validate.getValidationDate()));
        Mockito.when(credentialer.createCredentials(any(CreateCredentialsEvent.class)))
                .thenReturn(CreatedCredentialsEvent.success());

        ValidatedAccountSetupEvent validated = verificationService.validateAccountSetup(validate);
        assertTrue(validated.successful());
    }

    // FIXTURES

    private AccountValidation accountValidation() {
        return new AccountValidation("sdf090923940290u92", "password", new Date());
    }

    private RetrievedVerificationEvent expiredVerificationEvent(Date validationDate) {
        return RetrievedVerificationEvent.success(VerificationFixture.expiredVerification(validationDate));
    }

    private RetrievedVerificationEvent validVerificationEvent(Date validationDate) {
        return RetrievedVerificationEvent.success(VerificationFixture.validVerification(validationDate));
    }

}
