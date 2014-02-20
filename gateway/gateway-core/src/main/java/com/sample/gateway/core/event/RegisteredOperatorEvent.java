package com.sample.gateway.core.event;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public class RegisteredOperatorEvent {

    private final OperatorData data;

    public RegisteredOperatorEvent(OperatorData data) {
        this.data = data;
    }

    public OperatorData getData(){
        return data;
    }

    public Long getOperatorId(){
        return data.getOperatorId();
    }
}
