package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class RetrievedOperatorEvent implements ResponseEvent{

    private final Operator data;
    private final Status status;

    private RetrievedOperatorEvent(Operator data, Status status) {
        this.data = data;
        this.status = status;
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

    @Override
    public Status status() {
        return status;
    }
}
