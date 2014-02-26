package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class RetrievedOperatorEvent {

    private final Operator data;

    public RetrievedOperatorEvent(Operator data) {
        this.data = data;
    }

    public Operator getData() {
        return data;
    }
}
