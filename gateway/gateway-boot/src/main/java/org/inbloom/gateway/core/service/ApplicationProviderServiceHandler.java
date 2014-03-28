package org.inbloom.gateway.core.service;

import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.persistence.domain.UserEntity;
import org.inbloom.gateway.persistence.service.ApplicationProviderPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * created by benjaminmorgan on 3/24/14
 */
@Transactional
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
            return RegisteredApplicationProviderEvent.fail("A User with that email has already registered");
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

    @Transactional(readOnly = true)
    @Override
    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveAppProviderEvent) {

        return appProviderPersistenceService.retrieveApplicationProvider(retrieveAppProviderEvent);
    }
}
