package org.inbloom.gateway.core.event.verification;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class CreatedVerificationEvent extends BaseResponseEvent {

    private final Verification data;

    private CreatedVerificationEvent(Verification data, Status status, String message){
        this.data = data;
        this.setStatus(new GatewayStatus(status, message));
    }

    public Verification getData(){
        return data;
    }

    public boolean successful() {
        return Status.SUCCESS.equals(statusCode());
    }

    public static CreatedVerificationEvent notFound() {
        return new CreatedVerificationEvent(null, Status.NOT_FOUND, null);
    }

    public static CreatedVerificationEvent success(Verification verification) {
        return new CreatedVerificationEvent(verification, Status.SUCCESS, null);
    }

    public static CreatedVerificationEvent fail(Status status, String message) {
        return new CreatedVerificationEvent(null, status, message);
    }

    public static CreatedVerificationEvent notificationFail(String message) {
        return new CreatedVerificationEvent(null, Status.ERROR, message);
    }
}
