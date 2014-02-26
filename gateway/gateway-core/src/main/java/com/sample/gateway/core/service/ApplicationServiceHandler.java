package com.sample.gateway.core.service;

import com.sample.gateway.core.domain.Application;
import com.sample.gateway.core.event.*;
import com.sample.gateway.persistence.repository.ApplicationRepository;
import com.sample.gateway.persistence.service.ApplicationPersistenceService;
import com.sample.gateway.util.security.ApplicationKeyGeneratorFactory;
import com.sample.gateway.util.security.ApplicationKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
@Service
class ApplicationServiceHandler implements ApplicationService {

    @Autowired
    private ApplicationPersistenceService applicationPersistenceService;

    @Autowired
    private ApplicationKeyGeneratorFactory keyGeneratorFactory;

    @Override
    public RegisteredApplicationEvent registerNewApplication(RegisterApplicationEvent registerApplicationEvent) {
        Application application = registerApplicationEvent.getData();

        ApplicationKeyGenerator keyGenerator = keyGeneratorFactory.getKeyGenerator("SecureRandomKeyGenerator");

        application.approve(keyGenerator.generateClientId(), keyGenerator.generateSharedSecret());
        application.register();

        return applicationPersistenceService.registerApplication(new RegisterApplicationEvent(application));
    }

    @Override
    public RetrievedApplicationEvent retrieveApplication(RetrieveApplicationEvent retrieveApplicationEvent) {
        return applicationPersistenceService.retrieveApplication(retrieveApplicationEvent);
    }

    @Override
    public ModifiedApplicationEvent modifyApplication(ModifyApplicationEvent modifyApplicationEvent) {
        RetrievedApplicationEvent retrieved = retrieveApplication(new RetrieveApplicationEvent(modifyApplicationEvent.getApplicationId()));
        if (retrieved == null) {
            return ModifiedApplicationEvent.notFound(modifyApplicationEvent.getApplicationId());
        }
        return applicationPersistenceService.modifyApplication(modifyApplicationEvent);
    }
}
