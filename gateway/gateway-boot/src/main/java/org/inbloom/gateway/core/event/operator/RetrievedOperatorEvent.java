package org.inbloom.gateway.core.event.operator;

import org.inbloom.gateway.core.domain.Operator;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.status.OperatorStatus;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class RetrievedOperatorEvent implements ResponseEvent {

    private final Operator data;
    private final OperatorStatus status;

    private RetrievedOperatorEvent(Operator data, OperatorStatus status) {
        this.data = data;
        this.status = status;
    }

    public Operator getData() {
        return data;
    }

    public static RetrievedOperatorEvent success(Operator data) {
        return new RetrievedOperatorEvent(data, OperatorStatus.SUCCESS);
    }

    public static RetrievedOperatorEvent notFound() {
        return new RetrievedOperatorEvent(null, OperatorStatus.NOT_FOUND);
    }

    @Override
    public Status status() {
        return status;
    }
}
