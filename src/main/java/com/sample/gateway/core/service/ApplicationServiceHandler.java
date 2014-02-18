package com.sample.gateway.core.service;

import com.sample.gateway.core.domain.Application;
import com.sample.gateway.core.event.RegisterApplicationEvent;
import com.sample.gateway.core.event.RegisteredApplicationEvent;
import com.sample.gateway.persistence.service.ApplicationPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

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

    @Override
    public RegisteredApplicationEvent registerNewApplication(RegisterApplicationEvent registerApplicationEvent) {
        Application application = Application.fromApplicationData(registerApplicationEvent.getData());
        application.approve(generateClientId(application), generateSharedSecret(application));
        return applicationPersistenceService.registerApplication(new RegisterApplicationEvent(application.details()));
    }

    private String generateSharedSecret(Application application) {
        return UUID.randomUUID().toString();
    }

    private String generateClientId(Application application) {
        return application.getApplicationName() + "123456";
    }

}
