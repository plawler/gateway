package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Verification;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class VerifiedEmailEvent implements ResponseEvent{

    private final Verification data;
    private final Status status;

    private VerifiedEmailEvent(Verification data, Status status) {
        this.data = data;
        this.status = status;
    }

    @Override
    public Status status() {
        return status;
    }

    public static VerifiedEmailEvent success(Verification data){
        return new VerifiedEmailEvent(data, Status.SUCCESS);
    }

    public static VerifiedEmailEvent notFound(){
        return new VerifiedEmailEvent(null, Status.NOT_FOUND);
    }
}
