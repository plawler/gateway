package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public class RegisterOperatorEvent {

    private final Operator data;

    public RegisterOperatorEvent(Operator data) {
        this.data = data;
    }

    public Operator getData(){
        return data;
    }

}
