package org.inbloom.gateway.core.event.verification;

import org.inbloom.gateway.core.event.VerboseResponseEvent;
import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.common.status.Status;
import org.inbloom.gateway.common.status.VerificationStatus;

/**
 * Created By: paullawler
 */
public class ValidatedAccountSetupEvent implements VerboseResponseEvent {

    private final String message;
    private final VerificationStatus status;
    private Verification verification;

    private ValidatedAccountSetupEvent(VerificationStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    private ValidatedAccountSetupEvent(VerificationStatus status, Verification verification) {
        this.status = status;
        this.verification = verification;
        this.message = null;
    }

    public Status status() {
        return status;
    }

    @Override
    public String message() {
        return message;
    }

    public static ValidatedAccountSetupEvent failed(String message) {
        return new ValidatedAccountSetupEvent(VerificationStatus.ERROR, message);
    }

    public static ValidatedAccountSetupEvent success(Verification verification) {
        return new ValidatedAccountSetupEvent(VerificationStatus.SUCCESS, verification);
    }

    public boolean successful() {
        return VerificationStatus.SUCCESS.equals(status());
    }

    public Verification getData() {
        return verification;
    }

    public static ValidatedAccountSetupEvent notFound(String message) {
        return new ValidatedAccountSetupEvent(VerificationStatus.NOT_FOUND, message);
    }

    public static ValidatedAccountSetupEvent expired(String message) {
        return new ValidatedAccountSetupEvent(VerificationStatus.EXPIRED, message);
    }

    public static ValidatedAccountSetupEvent redeemed(String message) {
        return new ValidatedAccountSetupEvent(VerificationStatus.REDEEMED, message);
    }
}
