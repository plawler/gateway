package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.*;
import com.sample.gateway.persistence.domain.Application;
import com.sample.gateway.persistence.domain.ApplicationProvider;
import com.sample.gateway.persistence.repository.ApplicationProviderRepository;
import com.sample.gateway.persistence.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 */
@Repository
class ApplicationPersistenceHandler implements ApplicationPersistenceService {

    @Autowired
    ApplicationRepository applicationRepository;

    @Autowired
    ApplicationProviderRepository applicationProviderRepository;

    @Autowired
    ConversionService conversionService;

    @Override
    public RegisteredApplicationEvent registerApplication(RegisterApplicationEvent registerApplicationEvent) {
        Application application = conversionService.convert(registerApplicationEvent.getData(), com.sample.gateway.persistence.domain.Application.class);
        ApplicationProvider provider = applicationProviderRepository.findOne(registerApplicationEvent.getApplicationProviderId());
        application.setApplicationProvider(provider); // todo: validate the provider
        applicationRepository.save(application);
        return new RegisteredApplicationEvent(convertToDomain(application));
    }

    @Override
    public RetrievedApplicationEvent retrieveApplication(RetrieveApplicationEvent retrieveApplication) {
        Application application = applicationRepository.findOne(retrieveApplication.getApplicationId());
        return new RetrievedApplicationEvent(convertToDomain(application));
    }

    @Override
    public ModifiedApplicationEvent modifyApplication(ModifyApplicationEvent modifyApplicationEvent) {
        Application application = applicationRepository.findOne(modifyApplicationEvent.getApplicationId());

        application.setApplicationName(modifyApplicationEvent.getApplicationName());
        application.setDescription(modifyApplicationEvent.getDescription());
        application.setAppUri(modifyApplicationEvent.getAppUri());
        application.setRedirectUri(modifyApplicationEvent.getRedirectUri());
        application.setImageUri(modifyApplicationEvent.getImageUri());
        application.setAdmin(modifyApplicationEvent.isAdmin());
        application.setBulkExtract(modifyApplicationEvent.isBulkExtract());

        applicationRepository.save(application);

        return ModifiedApplicationEvent.newInstance(application.getApplicationId(), convertToDomain(application));
    }

    private com.sample.gateway.core.domain.Application convertToDomain(Application application) {
        return conversionService.convert(application, com.sample.gateway.core.domain.Application.class);
    }

}
