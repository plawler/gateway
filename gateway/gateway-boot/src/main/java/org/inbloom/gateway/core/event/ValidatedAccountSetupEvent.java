package org.inbloom.gateway.core.event;

/**
 * Created By: paullawler
 */
public class ValidatedAccountSetupEvent implements VerboseResponseEvent {

    private final String message;
    private final Status status;

    private ValidatedAccountSetupEvent(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    private ValidatedAccountSetupEvent(Status status) {
        this.status = status;
        this.message = null;
    }

    public ResponseEvent.Status status() {
        return status;
    }

    @Override
    public String message() {
        return message;
    }

    public static ValidatedAccountSetupEvent failed(String message) {
        return new ValidatedAccountSetupEvent(Status.FAILED, message);
    }

    public static ValidatedAccountSetupEvent success() {
        return new ValidatedAccountSetupEvent(Status.SUCCESS);
    }

}
