package com.sample.gateway.core.event;


/**
 * Created by lloydengebretsen on 2/18/14.
 */
public class RegisterApplicationProviderEvent {

    private final ApplicationProviderData data;

    public RegisterApplicationProviderEvent(ApplicationProviderData data) {
        this.data = data;
    }

    public ApplicationProviderData getData() {
        return data;
    }
}
