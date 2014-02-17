package com.sample.gateway.core.event;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 8:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class RegisteredApplicationEvent {

    private final ApplicationData dto;

    public RegisteredApplicationEvent(ApplicationData dto) {
        this.dto = dto;
    }

    public Long getApplicationId() {
        return dto.getApplicationId();
    }

    public String getApplicationName() {
        return dto.getApplicationName();
    }

    public String getDescription() {
        return dto.getDescription();
    }

    public String getAppUri() {
        return dto.getAppUri();
    }

    public String getRedirectUri() {
        return dto.getRedirectUri();
    }

    public String getAdminUri() {
        return dto.getAdminUri();
    }

    public String getImageUri() {
        return dto.getImageUri();
    }

    public Boolean isAdmin() {
        return dto.getAdmin();
    }

    public Boolean isBulkExtract() {
        return dto.getBulkExtract();
    }

    public Boolean isApproved() {
        return dto.isApproved();
    }

    public Date getApprovedOn() {
        return dto.getApprovedOn();
    }

    public Date getRegisteredOn() {
        return dto.getRegisteredOn();
    }

    public String getClientId() {
        return dto.getClientId();
    }

    public String getSharedSecret() {
        return dto.getSharedSecret();
    }

}
