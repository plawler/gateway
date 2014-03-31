package org.inbloom.gateway.core.service;

import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.*;
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

import java.util.Date;
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

    @Autowired
    Environment env;

    @Autowired
    VerificationPersistenceService verificationPersistenceService;

    @Autowired
    KeyGenerator keyGenerator;

    int VERIFICATION_TIMEOUT = 4*24*60*60*1000; //4 days

    private String getEmailTarget()
    {
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
        Date now = new Date();
        Date until = new Date(now.getTime() + VERIFICATION_TIMEOUT);
        verification.setValidFrom(now);
        verification.setValidUntil(until);

        //persist verification
        CreatedVerificationEvent createdEvent = verificationPersistenceService.createVerification(createEvent);

        if(createdEvent.status() == ResponseEvent.Status.SUCCESS) {

            //send email verification
            String confirmationLink = getEmailTarget() + "?token="+token;
            try {
                NotificationClient.getInstance().sendAccountRegistrationConfirmation(NotificationTypeEnum.EMAIL, user.getFirstName(), user.getEmail(), confirmationLink, Locale.ENGLISH);
            } catch (NotificationException e) {
                return CreatedVerificationEvent.fail("Notification Client failed to send email: " + e.getMessage());
            }
        }
        return createdEvent;
    }

    @Override
    public VerifiedEmailEvent verifyEmail(VerifyEmailEvent modifyEvent) {

        //TODO: update verification status to set "is_verified" to true

        //TODO: add user to LDAP

        //TODO: provision sandbox tenant

        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public RetrievedVerificationEvent retrieveVerification(RetrieveVerificationEvent retrieveEvent) {
        return verificationPersistenceService.retrieveVerification(retrieveEvent);
    }
}
