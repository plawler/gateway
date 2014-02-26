package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Application;

/**
 * Created by lloydengebretsen on 2/18/14.
 */
public class RetrievedApplicationEvent {

    private final Application data;

    public RetrievedApplicationEvent(Application data) {
        this.data = data;
    }

    public Application getData() {
        return data;
    }
}
