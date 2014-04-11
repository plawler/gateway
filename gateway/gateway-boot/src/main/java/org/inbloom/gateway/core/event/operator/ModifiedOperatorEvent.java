package org.inbloom.gateway.core.event.operator;


import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.common.status.OperatorStatus;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 2/27/14.
 */
public class ModifiedOperatorEvent implements ResponseEvent {

    private final Long id;
    private final Operator data;
    private final OperatorStatus status;

    private ModifiedOperatorEvent(Long id, Operator data, OperatorStatus status) {
        this.data = data;
        this.id = id;
        this.status = status;
    }

    public Operator getData() {
        return data;
    }

    public Long getId() {
        return id;
    }

    public static ModifiedOperatorEvent notFound(Long id)
    {
        return new ModifiedOperatorEvent(id, null, OperatorStatus.NOT_FOUND);
    }

    public static ModifiedOperatorEvent success(Long id, Operator modified)
    {
        return new ModifiedOperatorEvent(id, modified, OperatorStatus.SUCCESS);
    }

    @Override
    public Status status() {
        return this.status;
    }
}
