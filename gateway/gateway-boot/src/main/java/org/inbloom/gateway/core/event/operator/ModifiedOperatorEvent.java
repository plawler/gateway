package org.inbloom.gateway.core.event.operator;


import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.Operator;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 2/27/14.
 */
public class ModifiedOperatorEvent extends BaseResponseEvent{

    private final Long id;
    private final Operator data;

    private ModifiedOperatorEvent(Long id, Operator data, Status status) {
        this.data = data;
        this.id = id;
        this.setStatus(new GatewayStatus(status, null));
    }

    public Operator getData() {
        return data;
    }

    public Long getId() {
        return id;
    }

    public static ModifiedOperatorEvent notFound(Long id)
    {
        return new ModifiedOperatorEvent(id, null, Status.NOT_FOUND);
    }

    public static ModifiedOperatorEvent success(Long id, Operator modified)
    {
        return new ModifiedOperatorEvent(id, modified, Status.SUCCESS);
    }

}
