package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Verification;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class ModifiedVerificationEvent implements ResponseEvent{

    private final Verification data;
    private final Status status;

    private ModifiedVerificationEvent(Verification data, Status status) {
        this.data = data;
        this.status = status;
    }

    @Override
    public Status status() {
        return status;
    }

    public static ModifiedVerificationEvent success(Verification data){
        return new ModifiedVerificationEvent(data, Status.SUCCESS);
    }

    public static ModifiedVerificationEvent notFound(){
        return new ModifiedVerificationEvent(null, Status.NOT_FOUND);
    }
}
