package org.inbloom.gateway.core.event.operator;


import org.inbloom.gateway.core.domain.Operator;
import org.inbloom.gateway.core.event.RequestEvent;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public class RegisterOperatorEvent implements RequestEvent {

    private final Operator data;

    public RegisterOperatorEvent(Operator data) {
        this.data = data;
    }

    public Operator getData(){
        return data;
    }

}
