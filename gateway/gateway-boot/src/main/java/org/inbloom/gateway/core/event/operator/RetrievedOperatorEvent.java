package org.inbloom.gateway.core.event.operator;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class RetrievedOperatorEvent extends BaseResponseEvent {

    private final Operator data;

    private RetrievedOperatorEvent(Operator data, Status status) {
        this.data = data;
        this.setStatus(new GatewayStatus(status, null));
    }

    public Operator getData() {
        return data;
    }

    public static RetrievedOperatorEvent success(Operator data) {
        return new RetrievedOperatorEvent(data, Status.SUCCESS);
    }

    public static RetrievedOperatorEvent notFound() {
        return new RetrievedOperatorEvent(null, Status.NOT_FOUND);
    }

}
