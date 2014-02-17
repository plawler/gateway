package com.sample.gateway.core.service;

import com.sample.gateway.core.domain.Application;
import com.sample.gateway.core.event.RegisterApplicationEvent;
import com.sample.gateway.core.event.RegisteredApplicationEvent;
import com.sample.gateway.persistence.service.ApplicationPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        Application application = Application.fromApplicationData(registerApplicationEvent);

        registerApplicationEvent.setClientId(generateClientId(application)); // todo: i don't like this because we should not changing state. perhaps delegate to a new event
        registerApplicationEvent.setSharedSecret(generateSharedSecret(application));

        RegisteredApplicationEvent registeredEvent = applicationPersistenceService.registerApplication(registerApplicationEvent);

        doSomethingWithEvent(registeredEvent);

        return registeredEvent;
    }

    private String generateSharedSecret(Application application) {
        return null;
    }

    private String generateClientId(Application application) {
        return null;
    }

    private void doSomethingWithEvent(RegisteredApplicationEvent registeredEvent) {
        // todo: publish event?
    }

}
