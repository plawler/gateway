package org.inbloom.gateway.core.service;

import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.persistence.domain.UserEntity;
import org.inbloom.gateway.persistence.service.ApplicationProviderPersistenceService;
import org.inbloom.gateway.rest.validation.GatewayError;
import org.inbloom.gateway.rest.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by benjaminmorgan on 3/24/14
 */
@Service
public class ApplicationProviderServiceHandler implements ApplicationProviderService {

    @Autowired
    ApplicationProviderPersistenceService appProviderPersistenceService;

    @Autowired
    VerificationService verificationService;

    @Override
    public RegisteredApplicationProviderEvent registerApplicationProvider(RegisterApplicationProviderEvent registerAppProviderEvent) {

        //validate unique email
        String email = registerAppProviderEvent.getData().getUser().getEmail();
        UserEntity dbUser = appProviderPersistenceService.getUserByEmail(email);
        if(dbUser != null) {
            throw new ValidationException(GatewayError.USER_ALREADY_REGISTERED);
        }

        //persist the User and AppProvider
        RegisteredApplicationProviderEvent registeredEvent = appProviderPersistenceService.createApplicationProvider(registerAppProviderEvent);
        User user = registeredEvent.getData().getUser();

        //if we successfully created the user, create a verification
        if(registeredEvent.status() == ResponseEvent.Status.SUCCESS && user != null) {
            CreateVerificationEvent createEvent = new CreateVerificationEvent(user);
            CreatedVerificationEvent createdVerificationEvent = verificationService.createVerification(createEvent);

            if(!createdVerificationEvent.status().equals(ResponseEvent.Status.SUCCESS))
                return RegisteredApplicationProviderEvent.fail("Failed to Create Verification when registering App Provider");
        }

        return registeredEvent;
    }

    @Override
    public ModifiedApplicationProviderEvent modifyApplicationProvider(ModifyApplicationProviderEvent modifyAppProviderEvent) {

        return appProviderPersistenceService.modifyApplicationProvider(modifyAppProviderEvent);
    }

    @Override
    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveAppProviderEvent) {

        return appProviderPersistenceService.retrieveApplicationProvider(retrieveAppProviderEvent);
    }
}
