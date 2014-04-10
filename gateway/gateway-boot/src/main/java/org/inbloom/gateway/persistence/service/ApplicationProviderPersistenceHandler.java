package org.inbloom.gateway.persistence.service;

import org.apache.commons.lang.StringUtils;
import org.inbloom.gateway.common.domain.ApplicationProvider;
import org.inbloom.gateway.common.domain.User;
import org.inbloom.gateway.core.event.provider.*;
import org.inbloom.gateway.persistence.domain.ApplicationProviderEntity;
import org.inbloom.gateway.persistence.domain.BaseEntity;
import org.inbloom.gateway.persistence.domain.UserEntity;
import org.inbloom.gateway.persistence.repository.ApplicationProviderRepository;
import org.inbloom.gateway.persistence.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        setCreateData(appProviderEntity);
        applicationProviderRepository.save(appProviderEntity);

        UserEntity userEntity = conversionService.convert(registerApplicationProviderEvent.getData().getUser(), UserEntity.class);
        userEntity.setApplicationProviderId(appProviderEntity.getApplicationProviderId());
        setCreateData(userEntity);
        userRepository.save(userEntity);

        ApplicationProvider appProviderDomain = conversionService.convert(appProviderEntity, ApplicationProvider.class);
        appProviderDomain.setUser(conversionService.convert(userEntity, User.class));
        return RegisteredApplicationProviderEvent.success(appProviderDomain);
    }

    @Override
    public ModifiedApplicationProviderEvent modifyApplicationProvider(ModifyApplicationProviderEvent modifyApplicationProviderEvent) {
        ApplicationProviderEntity retrieved = applicationProviderRepository.findOne(modifyApplicationProviderEvent.getId());
        if(retrieved == null){
            return ModifiedApplicationProviderEvent.notFound();
        }

        retrieved.setApplicationProviderName(modifyApplicationProviderEvent.getApplicationProviderName());
        retrieved.setOrganizationName(modifyApplicationProviderEvent.getOrganizationName());
        setUpdateData(retrieved);
        applicationProviderRepository.save(retrieved);

        ApplicationProvider result = conversionService.convert(retrieved, ApplicationProvider.class);

        UserEntity retrievedUser = userRepository.findByApplicationProviderId(retrieved.getApplicationProviderId());
        setUpdateData(retrievedUser);
        if(StringUtils.isNotBlank(modifyApplicationProviderEvent.getUserEmail())){
            retrievedUser.setEmail(modifyApplicationProviderEvent.getUserEmail());
        }
        if(StringUtils.isNotBlank(modifyApplicationProviderEvent.getUserFirstName())){
            retrievedUser.setFirstName(modifyApplicationProviderEvent.getUserFirstName());
        }
        if(StringUtils.isNotBlank(modifyApplicationProviderEvent.getUserLastName())){
            retrievedUser.setLastName(modifyApplicationProviderEvent.getUserLastName());
        }
        userRepository.save(retrievedUser);
        result.setUser(conversionService.convert(retrievedUser, User.class));

        return ModifiedApplicationProviderEvent.success(result);
    }

    @Override
    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveApplicationProviderEvent) {
        ApplicationProviderEntity retrieved = applicationProviderRepository.findOne(retrieveApplicationProviderEvent.getId());
        if(retrieved == null) {
            return RetrievedApplicationProviderEvent.notFound();
        }
        else {
            UserEntity retrievedUser = userRepository.findByApplicationProviderId(retrieved.getApplicationProviderId());
            ApplicationProvider appProviderDomain = conversionService.convert(retrieved, ApplicationProvider.class);
            appProviderDomain.setUser(conversionService.convert(retrievedUser, User.class));
            return RetrievedApplicationProviderEvent.success(appProviderDomain);
        }
    }

    public UserEntity getUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    private void setCreateData(BaseEntity entity){
        entity.setCreatedAt(new Date());
        entity.setCreatedBy("System"); //TODO replace with actual user info after security model is implemented
    }

    private void setUpdateData(BaseEntity entity){
        entity.setUpdatedAt(new Date());
        entity.setUpdatedBy("System"); //TODO replace with actual user info after security model is implemented
    }
}
