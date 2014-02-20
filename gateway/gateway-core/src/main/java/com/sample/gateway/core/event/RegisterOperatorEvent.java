package com.sample.gateway.core.event;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public class RegisterOperatorEvent {

    private final OperatorData data;

    public RegisterOperatorEvent(OperatorData data) {
        this.data = data;
    }

    public OperatorData getData(){
        return data;
    }

}
