package com.sample.gateway.core.service;

import com.sample.gateway.core.event.RegisterApplicationProviderEvent;
import com.sample.gateway.core.event.RegisteredApplicationProviderEvent;
import com.sample.gateway.core.event.RetrieveApplicationProviderEvent;
import com.sample.gateway.core.event.RetrievedApplicationProviderEvent;
import com.sample.gateway.persistence.service.ApplicationProviderPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lloydengebretsen on 2/18/14.
 */
@Service
public class ApplicationProviderServiceHandler implements ApplicationProviderService {

    @Autowired
    ApplicationProviderPersistenceService applicationProviderPersistenceService;

    @Override
    public RegisteredApplicationProviderEvent registerApplicationProvider(RegisterApplicationProviderEvent registerApplicationProviderEvent) {
        return applicationProviderPersistenceService.registerApplicationProvider(registerApplicationProviderEvent);
    }

    @Override
    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveApplicationProviderEvent) {
        return applicationProviderPersistenceService.retrieveApplicationProvider(retrieveApplicationProviderEvent);
    }
}
