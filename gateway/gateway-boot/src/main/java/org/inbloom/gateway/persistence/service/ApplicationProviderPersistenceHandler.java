package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.core.domain.ApplicationProvider;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.persistence.domain.ApplicationProviderEntity;
import org.inbloom.gateway.persistence.repository.ApplicationProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
@Service
public class ApplicationProviderPersistenceHandler implements ApplicationProviderPersistenceService {

    @Autowired
    private ApplicationProviderRepository applicationProviderRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public RegisteredApplicationProviderEvent createApplicationProvider(RegisterApplicationProviderEvent registerApplicationProviderEvent) {
        ApplicationProviderEntity entity = conversionService.convert(registerApplicationProviderEvent.getData(), ApplicationProviderEntity.class);
        applicationProviderRepository.save(entity);
        return RegisteredApplicationProviderEvent.success(conversionService.convert(entity, ApplicationProvider.class));
    }

    @Override
    public ModifiedApplicationProviderEvent modifyApplicationProvider(ModifyApplicationProviderEvent modifyApplicationProviderEvent) {
        return null;
    }

    @Override
    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveApplicationProviderEvent) {
        return null;
    }
}
