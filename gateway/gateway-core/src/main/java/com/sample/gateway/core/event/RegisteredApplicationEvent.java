package com.sample.gateway.core.event;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 8:45 AM
 * To change this template use File | Settings | File Templates.
 */
public class RegisteredApplicationEvent {

    private final ApplicationData data;

    public RegisteredApplicationEvent(ApplicationData data) {
        this.data = data;
    }

    public ApplicationData getData() {
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

}
