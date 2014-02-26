package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Application;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 8:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class RegisteredApplicationEvent {

    private final Application data;

    public RegisteredApplicationEvent(Application data) {
        this.data = data;
    }

    public Application getData() {
        return data;
    }

    public Long getApplicationId() {
        return data.getApplicationId();
    }

    public String getClientId() {
        return data.getClientId();
    }

    public String getSharedSecret() {
        return data.getSharedSecret();
    }

    public Boolean getApproved() {
        return data.getApproved();
    }

    public Long getApplicationProviderId() {
        return data.getApplicationProviderId();
    }
}
