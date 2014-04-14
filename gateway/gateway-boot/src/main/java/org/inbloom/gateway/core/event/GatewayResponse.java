package org.inbloom.gateway.core.event;

import org.inbloom.gateway.common.status.GatewayStatus;

/**
 * @author benjaminmorgan
 *         Date: 4/11/14
 */
public class GatewayResponse<X> {

    private X payload;
    private GatewayAction action;
    private GatewayStatus status;

    public GatewayResponse(GatewayAction action, X payload, GatewayStatus status) {
        this.action = action;
        this.payload = payload;
        this.status = status;
    }

    public GatewayAction getAction() {
        return action;
    }

    public X getPayload() {
        return payload;
    }

    public GatewayStatus getStatus() {
        return status;
    }

}
