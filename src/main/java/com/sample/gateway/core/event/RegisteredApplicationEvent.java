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

    public Boolean isApproved() {
        return dto.isApproved();
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
