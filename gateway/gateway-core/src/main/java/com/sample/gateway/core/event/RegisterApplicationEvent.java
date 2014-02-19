package com.sample.gateway.core.event;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 8:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class RegisterApplicationEvent {

    private final ApplicationData data;

    public RegisterApplicationEvent(ApplicationData data) {
        this.data = data;
    }

    public ApplicationData getData() {
        return data;
    }

    public Long getApplicationProviderId() {
        return data.getApplicationProviderId();
    }

}
