package org.inbloom.gateway.core.event;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.common.status.Status;

/**
 * Created By: paullawler
 */
public interface ResponseEvent {

    GatewayStatus status();
    Status statusCode();
    String message();
}
