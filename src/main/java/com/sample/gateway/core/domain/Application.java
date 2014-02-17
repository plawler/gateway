package com.sample.gateway.core.domain;

import com.sample.gateway.core.event.RegisterApplicationEvent;

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
    private Boolean isAdmin;
    private Boolean isBulkExtract;
    private Boolean isApproved;
    private Date registeredOn;
    private Date approvedOn;

    public Application(RegisterApplicationEvent registerApplication) {
        this.adminUri = registerApplication.getAdminUri();
        this.applicationName = registerApplication.getApplicationName();
        this.appUri = registerApplication.getAppUri();
        this.description = registerApplication.getDescription();
        this.imageUri = registerApplication.getImageUri();
        this.redirectUri = registerApplication.getRedirectUri();
//        this.isAdmin = registerApplication.isAdmin();
//        this.isBulkExtract = registerApplication.isBulkExtract();
//        this.isApproved = registerApplication.isApproved()
        this.approvedOn = registerApplication.getApprovedOn();
        this.registeredOn = registerApplication.getRegisteredOn();
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
        return isAdmin;
    }

    public Boolean getBulkExtract() {
        return isBulkExtract;
    }

    public static Application fromApplicationData(RegisterApplicationEvent registerApplicationEvent) {
        return new Application(registerApplicationEvent);
    }
}
