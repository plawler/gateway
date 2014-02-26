package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class RetrieveOperatorEvent {

    private final Operator data;

    public RetrieveOperatorEvent(Operator data) {
        this.data = data;
    }

    public Long getId(){ return data.getOperatorId(); };
}
