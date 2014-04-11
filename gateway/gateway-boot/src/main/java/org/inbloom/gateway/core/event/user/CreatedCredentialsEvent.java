package org.inbloom.gateway.core.event.user;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.core.event.VerboseResponseEvent;
import org.inbloom.gateway.common.status.Status;

/**
 * Created By: paullawler
 */
public class CreatedCredentialsEvent extends BaseResponseEvent {

    private final String message;

    private CreatedCredentialsEvent(Status status, String message) {
        this.setStatus(new GatewayStatus(status, null));
        this.message = message;
    }

    public static CreatedCredentialsEvent failed(String message) {
        return new CreatedCredentialsEvent(Status.ERROR, message);
    }

    public static CreatedCredentialsEvent success() {
        return new CreatedCredentialsEvent(Status.SUCCESS, null);
    }

    public boolean successful() {
        return Status.SUCCESS.equals(statusCode());
    }

}
