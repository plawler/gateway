package org.inbloom.gateway.core.event.operator;


import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public class RegisteredOperatorEvent extends BaseResponseEvent {

    private final Operator data;

    private RegisteredOperatorEvent(Operator data, Status status) {
        this.data = data;
        this.setStatus(new GatewayStatus(status, null));
    }

    public static RegisteredOperatorEvent success(Operator data) {
        return new RegisteredOperatorEvent(data, Status.SUCCESS);
    }

    public static RegisteredOperatorEvent failed(Operator data) {
        return new RegisteredOperatorEvent(data, Status.ERROR);
    }


    public Operator getData(){
        return data;
    }

    public Long getOperatorId(){
        return data.getOperatorId();
    }

}
