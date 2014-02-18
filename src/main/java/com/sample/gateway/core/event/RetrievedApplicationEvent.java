package com.sample.gateway.core.event;

/**
 * Created by lloydengebretsen on 2/18/14.
 */
public class RetrievedApplicationEvent {
    private final ApplicationData data;

    public RetrievedApplicationEvent(ApplicationData data) {
        this.data = data;
    }

    public ApplicationData getData() {
        return data;
    }
}
