package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Verification;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class CreatedVerificationEvent implements ResponseEvent{

    private final Verification data;
    private final Status status;
    private final String message;

    private CreatedVerificationEvent(Verification data, Status status, String message){
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

    public static CreatedVerificationEvent notFound() {
        return new CreatedVerificationEvent(null, Status.NOT_FOUND, null);
    }

    public static CreatedVerificationEvent success(Verification verification) {
        return new CreatedVerificationEvent(verification, Status.SUCCESS, null);
    }

    public static CreatedVerificationEvent fail(String message) {
        return new CreatedVerificationEvent(null, Status.FAILED, message);
    }
}
