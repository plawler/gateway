package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.core.domain.ApplicationProvider;
import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.event.*;
import org.inbloom.gateway.persistence.domain.ApplicationProviderEntity;
import org.inbloom.gateway.persistence.domain.UserEntity;
import org.inbloom.gateway.persistence.repository.ApplicationProviderRepository;
import org.inbloom.gateway.persistence.repository.UserRepository;
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
    private UserRepository userRepository;

    @Autowired
    private ConversionService conversionService;

    @Override
    public RegisteredApplicationProviderEvent createApplicationProvider(RegisterApplicationProviderEvent registerApplicationProviderEvent) {
        ApplicationProviderEntity appProviderEntity = conversionService.convert(registerApplicationProviderEvent.getData(), ApplicationProviderEntity.class);

        applicationProviderRepository.save(appProviderEntity);

        UserEntity userEntity = conversionService.convert(registerApplicationProviderEvent.getData().getUser(), UserEntity.class);
        userEntity.setApplicationProviderId(appProviderEntity.getApplicationProviderId());
        userRepository.save(userEntity);

        ApplicationProvider appProviderDomain = conversionService.convert(appProviderEntity, ApplicationProvider.class);
        appProviderDomain.setUser(conversionService.convert(userEntity, User.class));
        return RegisteredApplicationProviderEvent.success(appProviderDomain);
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
