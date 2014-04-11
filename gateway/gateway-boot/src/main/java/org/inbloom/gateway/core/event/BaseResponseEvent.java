package org.inbloom.gateway.core.event;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 4/10/14.
 */
public abstract class BaseResponseEvent implements ResponseEvent{

    protected GatewayStatus status;

    protected void setStatus(GatewayStatus status)
    {
        this.status = status;
    }

    @Override
    public GatewayStatus status() {
        return status;
    }

    @Override
    public Status statusCode() {
        return status.getStatus();
    }

    @Override
    public String message() {
        return status.getMessage();
    }
}
