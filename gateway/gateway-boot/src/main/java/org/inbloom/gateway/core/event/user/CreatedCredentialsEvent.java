package org.inbloom.gateway.core.event.user;

import org.inbloom.gateway.core.event.VerboseResponseEvent;
import org.inbloom.gateway.common.status.CredentialStatus;
import org.inbloom.gateway.common.status.Status;

/**
 * Created By: paullawler
 */
public class CreatedCredentialsEvent implements VerboseResponseEvent {

    private final CredentialStatus status;
    private final String message;

    private CreatedCredentialsEvent(CredentialStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public Status status() {
        return status;
    }

    @Override
    public String message() {
        return message;
    }

    public static CreatedCredentialsEvent failed(String message) {
        return new CreatedCredentialsEvent(CredentialStatus.ERROR, message);
    }

    public static CreatedCredentialsEvent success() {
        return new CreatedCredentialsEvent(CredentialStatus.SUCCESS, null);
    }

    public boolean successful() {
        return status.equals(CredentialStatus.SUCCESS);
    }

}
