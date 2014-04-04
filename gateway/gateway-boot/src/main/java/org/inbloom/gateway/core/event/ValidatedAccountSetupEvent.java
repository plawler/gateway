package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Verification;

/**
 * Created By: paullawler
 */
public class ValidatedAccountSetupEvent implements VerboseResponseEvent {

    private final String message;
    private final Status status;
    private Verification verification;

    private ValidatedAccountSetupEvent(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    private ValidatedAccountSetupEvent(Status status, Verification verification) {
        this.status = status;
        this.verification = verification;
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

    public static ValidatedAccountSetupEvent success(Verification verification) {
        return new ValidatedAccountSetupEvent(Status.SUCCESS, verification);
    }

    public boolean successful() {
        return Status.SUCCESS.equals(status());
    }

    public Verification getData() {
        return verification;
    }

}
