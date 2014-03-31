package org.inbloom.gateway.core.service;


import org.inbloom.gateway.core.domain.AccountValidation;
import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.persistence.service.VerificationPersistenceService;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created By: paullawler
 */
@RunWith(MockitoJUnitRunner.class)
public class VerificationServiceTest {

    @Mock
    private VerificationPersistenceService verificationPersistence;

    private VerificationServiceHandler verificationService;

    @Before
    public void setUp() {
        verificationService = new VerificationServiceHandler(verificationPersistence);
    }

    @Test
    public void shouldFailIfVerificationHasExpired() {
        ValidateAccountSetupEvent validate = new ValidateAccountSetupEvent(accountValidation());

        Mockito.when(verificationPersistence.retrieveForAccountValidation(validate))
                .thenReturn(expiredVerificationEvent(validate.getValidationDate()));

        ValidatedAccountSetupEvent validated = verificationService.validateAccountSetup(validate);

        assertEquals(ResponseEvent.Status.FAILED, validated.status());
    }

    // FIXTURES

    private AccountValidation accountValidation() {
        return new AccountValidation("sdf090923940290u92", "password", new Date());
    }

    private RetrievedVerificationEvent expiredVerificationEvent(Date validationDate) {
        DateTime validFromDate = new DateTime(validationDate).minusDays(5);
        DateTime validUntilDate = validFromDate.plus(3);

        Verification expired = new Verification();
        expired.setVerificationId(123456L);
        expired.setValidFrom(validFromDate.toDate());
        expired.setValidUntil(validUntilDate.toDate());

        return new RetrievedVerificationEvent(expired, ResponseEvent.Status.SUCCESS);
    }

}
