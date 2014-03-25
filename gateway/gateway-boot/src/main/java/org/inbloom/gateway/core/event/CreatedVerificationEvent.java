package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Token;
import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.domain.Verification;

import java.util.Date;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class CreatedVerificationEvent implements ResponseEvent{

    private final Verification data;
    private final Status status;

    private CreatedVerificationEvent(Verification data, Status status){
        this.data = data;
        this.status = status;
    }

    public Verification getData(){
        return data;
    }

    @Override
    public Status status() {
        return status;
    }

    public static CreatedVerificationEvent notFound() {
        return new CreatedVerificationEvent(null, Status.NOT_FOUND);
    }

    public static CreatedVerificationEvent success(Verification verification) {
        return new CreatedVerificationEvent(verification, Status.SUCCESS);
    }
}
