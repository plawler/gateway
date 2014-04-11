package org.inbloom.gateway.core.event.verification;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.common.status.Status;

/**
 * Created By: paullawler
 */
public class ValidatedAccountSetupEvent extends BaseResponseEvent {

    private Verification verification;

    private ValidatedAccountSetupEvent(Status status, String message) {
        this.setStatus(new GatewayStatus(status, message));
    }

    private ValidatedAccountSetupEvent(Status status, Verification verification) {
        this.setStatus(new GatewayStatus(status, null));
        this.verification = verification;
    }

    public static ValidatedAccountSetupEvent failed(String message) {
        return new ValidatedAccountSetupEvent(Status.ERROR, message);
    }

    public static ValidatedAccountSetupEvent success(Verification verification) {
        return new ValidatedAccountSetupEvent(Status.SUCCESS, verification);
    }

    public boolean successful() {
        return Status.SUCCESS.equals(statusCode());
    }

    public Verification getData() {
        return verification;
    }

    public static ValidatedAccountSetupEvent notFound(String message) {
        return new ValidatedAccountSetupEvent(Status.NOT_FOUND, message);
    }

    public static ValidatedAccountSetupEvent expired(String message) {
        return new ValidatedAccountSetupEvent(Status.EXPIRED, message);
    }

    public static ValidatedAccountSetupEvent redeemed(String message) {
        return new ValidatedAccountSetupEvent(Status.REDEEMED, message);
    }
}
