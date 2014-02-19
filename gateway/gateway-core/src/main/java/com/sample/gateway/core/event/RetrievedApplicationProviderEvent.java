package com.sample.gateway.core.event;



/**
 * Created by lloydengebretsen on 2/18/14.
 */
public class RetrievedApplicationProviderEvent {

    private final ApplicationProviderData data;

    public RetrievedApplicationProviderEvent(ApplicationProviderData data) {
        this.data = data;
    }

    public ApplicationProviderData getData() {
        return data;
    }
}
