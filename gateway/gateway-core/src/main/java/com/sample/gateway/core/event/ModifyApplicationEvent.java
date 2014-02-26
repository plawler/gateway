package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Application;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/18/14
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModifyApplicationEvent {

    private final Application application;
    private final Long applicationId;

    public ModifyApplicationEvent(Long applicationId, Application application) {
        this.application = application;
        this.applicationId = applicationId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public String getApplicationName() {
        return application.getApplicationName();
    }

    public String getDescription() {
        return application.getDescription();
    }

    public String getAppUri() {
        return application.getAppUri();
    }

    public String getRedirectUri() {
        return application.getRedirectUri();
    }

    public String getImageUri() {
        return application.getImageUri();
    }

    public Boolean isAdmin() {
        return application.getAdmin();
    }

    public Boolean isBulkExtract() {
        return application.getBulkExtract();
    }

}
