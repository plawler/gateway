package com.sample.gateway.core.domain;

import com.sample.gateway.core.event.ApplicationData;
import com.sample.gateway.core.event.RegisterApplicationEvent;
import com.sample.gateway.core.event.RegisteredApplicationEvent;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 8:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class Application {

    private Long applicationId;
    private String clientId;
    private String sharedSecret;
    private String applicationName;
    private String description;
    private String appUri;
    private String redirectUri;
    private String adminUri;
    private String imageUri;
    private Boolean admin;
    private Boolean bulkExtract;
    private Boolean approved;
    private Date registeredOn;
    private Date approvedOn;

    private Application() {}

    public static Application fromApplicationData(RegisterApplicationEvent registerApplicationEvent) {
        Application application = new Application();
        BeanUtils.copyProperties(registerApplicationEvent, application);
        return application;
    }

    public static Application fromApplicationData(RegisteredApplicationEvent registeredApplicationEvent) {
        Application application = new Application();
        BeanUtils.copyProperties(registeredApplicationEvent, application);
        return application;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getDescription() {
        return description;
    }

    public String getAppUri() {
        return appUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public String getAdminUri() {
        return adminUri;
    }

    public String getImageUri() {
        return imageUri;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public Boolean getBulkExtract() {
        return bulkExtract;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public Boolean getApproved() {
        return approved;
    }

    public Date getRegisteredOn() {
        return registeredOn;
    }

    public Date getApprovedOn() {
        return approvedOn;
    }

    public ApplicationData details() {
        ApplicationData data = new ApplicationData();
        BeanUtils.copyProperties(this, data);
        return data;
    }

}
