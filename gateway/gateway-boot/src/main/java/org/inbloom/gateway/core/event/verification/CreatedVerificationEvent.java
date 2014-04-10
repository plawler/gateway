package org.inbloom.gateway.core.event.verification;

import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.common.status.Status;
import org.inbloom.gateway.common.status.VerificationStatus;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class CreatedVerificationEvent implements ResponseEvent {

    private final Verification data;
    private final VerificationStatus status;
    private final String message;

    private CreatedVerificationEvent(Verification data, VerificationStatus status, String message){
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public Verification getData(){
        return data;
    }

    @Override
    public Status status() {
        return status;
    }

    public boolean successful() {
        return VerificationStatus.SUCCESS.equals(status());
    }

    public static CreatedVerificationEvent notFound() {
        return new CreatedVerificationEvent(null, VerificationStatus.NOT_FOUND, null);
    }

    public static CreatedVerificationEvent success(Verification verification) {
        return new CreatedVerificationEvent(verification, VerificationStatus.SUCCESS, null);
    }

    public static CreatedVerificationEvent fail(VerificationStatus status, String message) {
        return new CreatedVerificationEvent(null, status, message);
    }

    public static CreatedVerificationEvent notificationFail(String message) {
        return new CreatedVerificationEvent(null, VerificationStatus.NOTIFICATION_FAILED, message);
    }
}
