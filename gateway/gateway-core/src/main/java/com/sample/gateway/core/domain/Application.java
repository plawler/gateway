package com.sample.gateway.core.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sample.gateway.util.JsonDateSerializer;

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
    private Long applicationProviderId;
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

    public static Application newInstance(Long providerId, String name, String uri) {
        Application application = new Application();
        application.setApplicationProviderId(providerId);
        application.setApplicationName(name);
        application.setAppUri(uri);
        return application;
    }

    public Long getApplicationProviderId() {
        return applicationProviderId;
    }

    public void setApplicationProviderId(Long applicationProviderId) {
        this.applicationProviderId = applicationProviderId;
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

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getRegisteredOn() {
        return registeredOn;
    }

    @JsonSerialize(using = JsonDateSerializer.class)
    public Date getApprovedOn() {
        return approvedOn;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAppUri(String appUri) {
        this.appUri = appUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public void setAdminUri(String adminUri) {
        this.adminUri = adminUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public void setBulkExtract(Boolean bulkExtract) {
        this.bulkExtract = bulkExtract;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public void setRegisteredOn(Date registeredOn) {
        this.registeredOn = registeredOn;
    }

    public void setApprovedOn(Date approvedOn) {
        this.approvedOn = approvedOn;
    }

    public void register() {
        this.registeredOn = new Date();
    }

    public void approve(String clientId, String sharedSecret) {
        this.clientId = clientId;
        this.sharedSecret = sharedSecret;
        this.approved = true;
        this.approvedOn = new Date();
    }

}
