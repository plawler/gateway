package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.*;
import com.sample.gateway.persistence.domain.ApplicationEntity;
import com.sample.gateway.persistence.domain.ApplicationProviderEntity;
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
        ApplicationEntity applicationEntity = conversionService.convert(registerApplicationEvent.getData(), ApplicationEntity.class);
        ApplicationProviderEntity provider = applicationProviderRepository.findOne(registerApplicationEvent.getApplicationProviderId());
        applicationEntity.setApplicationProviderEntity(provider); // todo: validate the provider
        applicationRepository.save(applicationEntity);
        return new RegisteredApplicationEvent(convertToDomain(applicationEntity));
    }

    @Override
    public RetrievedApplicationEvent retrieveApplication(RetrieveApplicationEvent retrieveApplication) {
        ApplicationEntity applicationEntity = applicationRepository.findOne(retrieveApplication.getApplicationId());
        return new RetrievedApplicationEvent(convertToDomain(applicationEntity));
    }

    @Override
    public ModifiedApplicationEvent modifyApplication(ModifyApplicationEvent modifyApplicationEvent) {
        ApplicationEntity applicationEntity = applicationRepository.findOne(modifyApplicationEvent.getApplicationId());

        applicationEntity.setApplicationName(modifyApplicationEvent.getApplicationName());
        applicationEntity.setDescription(modifyApplicationEvent.getDescription());
        applicationEntity.setAppUri(modifyApplicationEvent.getAppUri());
        applicationEntity.setRedirectUri(modifyApplicationEvent.getRedirectUri());
        applicationEntity.setImageUri(modifyApplicationEvent.getImageUri());
        applicationEntity.setAdmin(modifyApplicationEvent.isAdmin());
        applicationEntity.setBulkExtract(modifyApplicationEvent.isBulkExtract());

        applicationRepository.save(applicationEntity);

        return ModifiedApplicationEvent.newInstance(applicationEntity.getApplicationId(), convertToDomain(applicationEntity));
    }

    private com.sample.gateway.core.domain.Application convertToDomain(ApplicationEntity applicationEntity) {
        return conversionService.convert(applicationEntity, com.sample.gateway.core.domain.Application.class);
    }

}
