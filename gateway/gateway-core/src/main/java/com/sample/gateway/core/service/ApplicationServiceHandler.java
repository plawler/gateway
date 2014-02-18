package com.sample.gateway.core.service;

import com.sample.gateway.core.domain.Application;
import com.sample.gateway.core.event.*;
import com.sample.gateway.persistence.service.ApplicationPersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
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


    private static final int CLIENT_ID_LENGTH = 10;
    private static final int CLIENT_SECRET_LENGTH = 48;
    private static final char[] DEFAULT_CODEC = "1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static Random random = new SecureRandom();

    @Autowired
    private ApplicationPersistenceService applicationPersistenceService;

    @Override
    public RegisteredApplicationEvent registerNewApplication(RegisterApplicationEvent registerApplicationEvent) {
        Application application = Application.fromApplicationData(registerApplicationEvent.getData());
        application.approve(generateClientId(application), generateSharedSecret(application));
        return applicationPersistenceService.registerApplication(new RegisterApplicationEvent(application.details()));
    }

    @Override
    public RetrievedApplicationEvent retrieveApplication(RetrieveApplicationEvent retrieveApplicationEvent) {
        return applicationPersistenceService.retrieveApplication(retrieveApplicationEvent);
    }

    public ModifiedApplicationEvent modifyApplication(ModifyApplicationEvent modifyApplicationEvent) {
        RetrievedApplicationEvent retrieved = retrieveApplication(new RetrieveApplicationEvent(modifyApplicationEvent.getApplicationId()));
        Application application = Application.fromApplicationData(retrieved.getData());
        application.modify(modifyApplicationEvent);
        return applicationPersistenceService.modifyApplication(new ModifyApplicationEvent(application.details()));
    }

    private String generateSharedSecret(Application application) {
        return UUID.randomUUID().toString();
    }

    private String generateClientId(Application application) {
        return application.getApplicationName().replace(" ", "") + "123456";
    }

}
