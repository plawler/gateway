package com.sample.gateway.core.event;

/**
 * Created by lloydengebretsen on 2/18/14.
 */
public class RetrieveApplicationProviderEvent {

    private final Long id;

    public RetrieveApplicationProviderEvent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
