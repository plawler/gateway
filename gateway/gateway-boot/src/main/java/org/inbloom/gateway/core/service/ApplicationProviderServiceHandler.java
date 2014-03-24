package org.inbloom.gateway.core.service;

import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.persistence.service.ApplicationProviderPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created by benjaminmorgan on 3/24/14
 */
@Service
public class ApplicationProviderServiceHandler implements ApplicationProviderService {

    @Autowired
    ApplicationProviderPersistenceService appProviderPersistenceService;

    @Override
    public RegisteredApplicationProviderEvent registerApplicationProvider(RegisterApplicationProviderEvent registerAppProviderEvent) {

        RegisteredApplicationProviderEvent registeredEvent = appProviderPersistenceService.createApplicationProvider(registerAppProviderEvent);

        //TODO: send verification email

        return registeredEvent;
    }

    @Override
    public ModifiedApplicationProviderEvent modifyApplicationProvicer(ModifyApplicationProviderEvent modifyAppProviderEvent) {

        return appProviderPersistenceService.modifyApplicationProvider(modifyAppProviderEvent);
    }

    @Override
    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveAppProviderEvent) {

        return appProviderPersistenceService.retrieveApplicationProvider(retrieveAppProviderEvent);
    }
}
