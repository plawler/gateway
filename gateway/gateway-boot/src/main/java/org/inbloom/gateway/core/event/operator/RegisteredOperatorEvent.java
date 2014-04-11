package org.inbloom.gateway.core.event.operator;


import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.common.status.OperatorStatus;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public class RegisteredOperatorEvent implements ResponseEvent {

    private final Operator data;
    private final OperatorStatus status;

    private RegisteredOperatorEvent(Operator data, OperatorStatus status) {
        this.data = data;
        this.status = status;
    }

    public static RegisteredOperatorEvent success(Operator data) {
        return new RegisteredOperatorEvent(data, OperatorStatus.SUCCESS);
    }

    public static RegisteredOperatorEvent failed(Operator data) {
        return new RegisteredOperatorEvent(data, OperatorStatus.ERROR);
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
