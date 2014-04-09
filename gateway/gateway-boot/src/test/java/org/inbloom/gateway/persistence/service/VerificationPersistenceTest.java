package org.inbloom.gateway.persistence.service;


import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.core.domain.AccountValidation;
import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.core.service.VerificationService;
import org.inbloom.gateway.fixture.ApplicationProviderFixture;
import org.junit.Before;
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
 * Created By: paullawler
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Gateway.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class VerificationPersistenceTest {

    @Autowired
    private ApplicationProviderPersistenceService providerService;

    @Autowired
    private VerificationPersistenceService verificationService;

    private CreatedVerificationEvent created;

    @Before
    public void setUp() {
        RegisterApplicationProviderEvent register = new RegisterApplicationProviderEvent(ApplicationProviderFixture.buildAppProvider1(null));
        RegisteredApplicationProviderEvent registered = providerService.createApplicationProvider(register);
        created = verificationService.createVerification(new CreateVerificationEvent(registered.getData().getUser()));
    }

    @Test
    public void shouldRetrieveAVerificationForAccountValidation() {
        AccountValidation accountValidation = new AccountValidation(created.getData().getToken(), "passwerd");
        RetrievedVerificationEvent retrieved = verificationService.retrieveForAccountValidation(new ValidateAccountSetupEvent(accountValidation));
        assertNotNull(retrieved.getData().getUser());
    }

    @Test
    public void shouldReturnNotFoundIfInvalidToken() {
        RetrievedVerificationEvent retrieved = verificationService.retrieveVerification(new RetrieveVerificationEvent("i-am-an-invalid-token"));
        assertTrue(retrieved.notFound());
    }

    @Test
    public void shouldModifyAVerification() {
        Verification verification = created.getData();
        verification.activate(VerificationService.VERIFICATION_TIMEOUT);
        ModifiedVerificationEvent modified = verificationService.modifyVerification(new ModifyVerificationEvent(verification));
        assertTrue(modified.successful());
    }

}