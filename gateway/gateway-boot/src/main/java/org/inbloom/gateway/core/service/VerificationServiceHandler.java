package org.inbloom.gateway.core.service;

import org.inbloom.gateway.core.domain.Credentials;
import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.credentials.CredentialService;
import org.inbloom.gateway.persistence.service.VerificationPersistenceService;
import org.inbloom.gateway.util.keyService.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: benjaminmorgan
 * Date: 3/25/14
 * Time: 8:49 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
public class VerificationServiceHandler implements VerificationService {

    private final VerificationPersistenceService persistenceService;
    private final CredentialService credentialService;

    @Autowired
    KeyGenerator keyGenerator;

    static final int VERIFICATION_TIMEOUT = 4*24*60*60*1000; //4 days

    @Autowired
    public VerificationServiceHandler(VerificationPersistenceService persistenceService, CredentialService credentialService) {
        this.persistenceService = persistenceService;
        this.credentialService = credentialService;
    }

    @Override
    public CreatedVerificationEvent createVerification(CreateVerificationEvent createEvent) {

        Verification verification = createEvent.getData();

        //generate verification token
        String token = keyGenerator.generateKey();
        verification.setToken(token);

        //set valid time range
        Date now = new Date();
        Date until = new Date(now.getTime() + VERIFICATION_TIMEOUT);
        verification.setValidFrom(now);
        verification.setValidUntil(until);

        //persist verification
        CreatedVerificationEvent createdEvent = persistenceService.createVerification(createEvent);

        if(createdEvent.status() == ResponseEvent.Status.SUCCESS) {
            //TODO: send verification email
            System.out.println("sending verification email w/ token: " + token);
        }
        return createdEvent;
    }

    @Override
    public ValidatedAccountSetupEvent validateAccountSetup(ValidateAccountSetupEvent validateEvent) {
        RetrievedVerificationEvent retrieved = persistenceService.retrieveForAccountValidation(validateEvent);
        if (retrieved.status().equals(ResponseEvent.Status.NOT_FOUND)) {
            return ValidatedAccountSetupEvent.failed("The verification could not be found. Either an invalid token was supplied or the account does not exist.");
        }

        Verification verification = retrieved.getData();
        if (verification.invalid()) {
            return ValidatedAccountSetupEvent.failed("The verification is no longer valid");
        }

        if (!processCredentials(verification.createCredentials(validateEvent.getPassword())))
            return ValidatedAccountSetupEvent.failed("Storing the user credentials failed.");

        // bootstrap step

        return ValidatedAccountSetupEvent.success();

    }

    @Override
    public ModifiedVerificationEvent modifyVerification(ModifyVerificationEvent modifyEvent) {

        //TODO: update verification status to set "is_verified" to true

        //TODO: add user to LDAP

        //TODO: provision sandbox tenant

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public RetrievedVerificationEvent retrieveVerification(RetrieveVerificationEvent retrieveEvent) {
        return persistenceService.retrieveVerification(retrieveEvent);
    }

    private boolean processCredentials(Credentials credentials) {
        CreatedCredentialsEvent created = credentialService.createCredentials(new CreateCredentialsEvent(credentials));
        return created.successful();
    }

}
