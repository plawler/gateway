package com.sample.gateway.core.event;


/**
 * Created by lloydengebretsen on 2/18/14.
 */
public class RegisteredApplicationProviderEvent {

    private final ApplicationProviderData data;

    public RegisteredApplicationProviderEvent(ApplicationProviderData data) {
        this.data = data;
    }

    public ApplicationProviderData getData() {
        return data;
    }
}
