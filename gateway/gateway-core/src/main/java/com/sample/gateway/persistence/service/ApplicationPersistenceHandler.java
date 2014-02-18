package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.*;
import com.sample.gateway.persistence.domain.Application;
import com.sample.gateway.persistence.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // todo: instead of passing through the business events, how about CRUD events?
    @Override
    public RegisteredApplicationEvent registerApplication(RegisterApplicationEvent registerApplicationEvent) {
        Application application = Application.newInstanceFrom(registerApplicationEvent);
        applicationRepository.save(application);
        return new RegisteredApplicationEvent(application.details());
    }

    @Override
    public RetrievedApplicationEvent retrieveApplication(RetrieveApplicationEvent retrieveApplication) {
        Application app = applicationRepository.findOne(retrieveApplication.getApplicationId());
        return new RetrievedApplicationEvent(app.details());
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

        return new ModifiedApplicationEvent(application.details());
    }

}
