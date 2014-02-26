package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.*;
import com.sample.gateway.persistence.domain.ApplicationProviderEntity;
import com.sample.gateway.persistence.repository.ApplicationProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by lloydengebretsen on 2/18/14.
 */
@Repository
public class ApplicationProviderPersistenceHandler implements ApplicationProviderPersistenceService {

    @Autowired
    ApplicationProviderRepository applicationProviderRepository;

    @Override
    public RegisteredApplicationProviderEvent registerApplicationProvider(RegisterApplicationProviderEvent registerApplicationProviderEvent) {
        ApplicationProviderEntity provider = ApplicationProviderEntity.instanceFrom(registerApplicationProviderEvent);
        applicationProviderRepository.save(provider);
        RegisteredApplicationProviderEvent response = new RegisteredApplicationProviderEvent(provider.details());
        return response;
    }

    @Override
    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveApplicationProviderEvent) {
        ApplicationProviderEntity appProvider = applicationProviderRepository.findOne(retrieveApplicationProviderEvent.getId());
        RetrievedApplicationProviderEvent response = new RetrievedApplicationProviderEvent(appProvider.details());
        return response;
    }
}
