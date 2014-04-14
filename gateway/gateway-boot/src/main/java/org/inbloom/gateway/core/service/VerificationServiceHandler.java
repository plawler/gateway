package org.inbloom.gateway.core.service;

import org.inbloom.gateway.common.domain.Credentials;
import org.inbloom.gateway.common.domain.User;
import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.common.status.Status;
import org.inbloom.gateway.core.event.GatewayAction;
import org.inbloom.gateway.core.event.GatewayRequest;
import org.inbloom.gateway.core.event.GatewayResponse;
import org.inbloom.gateway.core.event.verification.*;
import org.inbloom.gateway.credentials.CredentialService;
import org.inbloom.gateway.persistence.service.VerificationPersistenceService;
import org.inbloom.gateway.util.keyService.KeyGenerator;
import org.inbloom.notification.client.NotificationClient;
import org.inbloom.notification.client.NotificationException;
import org.inbloom.notification.client.NotificationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * User: benjaminmorgan
 * Date: 3/25/14
 * Time: 8:49 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
@Transactional
@PropertySource("classpath:application.properties")
public class VerificationServiceHandler implements VerificationService{

    private final VerificationPersistenceService persistenceService;
    private final CredentialService credentialService;
    private final KeyGenerator keyGenerator;
    private final Environment env;

    @Autowired
    public VerificationServiceHandler(VerificationPersistenceService persistenceService, CredentialService credentialService,
                                      KeyGenerator keyGenerator, Environment env) {
        this.persistenceService = persistenceService;
        this.credentialService = credentialService;
        this.keyGenerator = keyGenerator;
        this.env = env;
    }

    private String getEmailTarget() {
        return env.getProperty("emailVerificationLinkTarget","https://portal.inbloom.org/email_verification");
    }

    @Override
    public CreatedVerificationEvent createVerification(CreateVerificationEvent createEvent) {

        Verification verification = createEvent.getData();

        User user = verification.getUser();

        //TODO: delete other verifications?

        //generate verification token
        String token = keyGenerator.generateKey();
        verification.setToken(token);

        //set valid time range
        verification.activate(VERIFICATION_TIMEOUT);

        //persist verification
        CreatedVerificationEvent createdEvent = persistenceService.createVerification(createEvent);

        if(createdEvent.successful()) {
            try {
                sendNotification(user, token); //send email verification
            } catch (NotificationException e) {
                return CreatedVerificationEvent.notificationFail("Notification Client failed to send email: " + e.getMessage());
            }
        }
        return createdEvent;
    }

    @Override
    public ValidatedAccountSetupEvent validateAccountSetup(ValidateAccountSetupEvent validateEvent) {
        RetrievedVerificationEvent retrieved = persistenceService.retrieveForAccountValidation(validateEvent);
        if (retrieved.statusCode().equals(Status.NOT_FOUND)) {
            return ValidatedAccountSetupEvent.notFound("The verification could not be found. Either an invalid token was supplied or the account does not exist.");
        }

        Verification verification = retrieved.getData();
        if (verification.isExpired()) {
            return ValidatedAccountSetupEvent.expired("The verification is no longer valid");
        }
        if(verification.isVerified()) {
            return ValidatedAccountSetupEvent.redeemed("The verification token has already been redeemed");
        }

        if (!processCredentials(verification.createCredentials(validateEvent.getPassword())))
            return ValidatedAccountSetupEvent.failed("Storing the user credentials failed.");

        verification.validate();
        if (!modifyVerification(new ModifyVerificationEvent(verification)).successful()) {
            return ValidatedAccountSetupEvent.failed("Updating the verification failed.");
        }

        return ValidatedAccountSetupEvent.success(verification);
    }

    @Override
    public ModifiedVerificationEvent modifyVerification(ModifyVerificationEvent modifyEvent) {
        return persistenceService.modifyVerification(modifyEvent);
    }

    private void sendNotification(User user, String token) throws NotificationException {
        String confirmationLink = getEmailTarget() + "?token="+token;
        NotificationClient.getInstance().sendAccountRegistrationConfirmation(NotificationTypeEnum.EMAIL, user.getFirstName(), user.getEmail(), confirmationLink, Locale.ENGLISH);
    }

    @Override
    @Transactional(readOnly = true)
    public RetrievedVerificationEvent retrieveVerification(RetrieveVerificationEvent retrieveEvent) {
        return persistenceService.retrieveVerification(retrieveEvent);
    }

    private boolean processCredentials(Credentials credentials) {
        GatewayResponse<Credentials> created = credentialService.createCredentials(new GatewayRequest<Credentials>(GatewayAction.CREATE, credentials));
        return created.getStatus().equals(Status.SUCCESS);
    }

}
