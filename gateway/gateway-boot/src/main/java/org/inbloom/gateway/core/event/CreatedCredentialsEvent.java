package org.inbloom.gateway.core.event;

/**
 * Created By: paullawler
 */
public class CreatedCredentialsEvent implements VerboseResponseEvent {

    private final Status status;
    private final String message;

    private CreatedCredentialsEvent(Status status, String message) {
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
        return new CreatedCredentialsEvent(Status.FAILED, message);
    }

    public static CreatedCredentialsEvent success() {
        return new CreatedCredentialsEvent(Status.SUCCESS, null);
    }
}
