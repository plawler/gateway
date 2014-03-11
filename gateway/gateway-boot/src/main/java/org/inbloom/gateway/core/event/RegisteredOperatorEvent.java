package org.inbloom.gateway.core.event;


import org.inbloom.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public class RegisteredOperatorEvent implements ResponseEvent{

    private final Operator data;
    private final Status status;

    private RegisteredOperatorEvent(Operator data, Status status) {
        this.data = data;
        this.status = status;
    }

    public static RegisteredOperatorEvent success(Operator data) {
        return new RegisteredOperatorEvent(data, Status.SUCCESS);
    }

    public static RegisteredOperatorEvent failed(Operator data) {
        return new RegisteredOperatorEvent(data, Status.FAILED);
    }


    public Operator getData(){
        return data;
    }

    public Long getOperatorId(){
        return data.getOperatorId();
    }

    @Override
    public Status status() {
        return null;
    }
}
