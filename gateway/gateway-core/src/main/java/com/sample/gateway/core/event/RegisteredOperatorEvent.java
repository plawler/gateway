package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public class RegisteredOperatorEvent {

    private final Operator data;

    public RegisteredOperatorEvent(Operator data) {
        this.data = data;
    }

    public Operator getData(){
        return data;
    }

    public Long getOperatorId(){
        return data.getOperatorId();
    }
}
