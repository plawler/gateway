package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Verification;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class RetrievedVerificationEvent implements ResponseEvent{

    private final Verification data;
    private final Status status;

    private RetrievedVerificationEvent(Verification data, Status status) {
        this.data = data;
        this.status=status;
    }

    public Verification getData() {
        return data;
    }

    @Override
    public Status status() {
        return status;
    }

    public static RetrievedVerificationEvent success(Verification verification)
    {
        return new RetrievedVerificationEvent(verification, Status.SUCCESS);
    }

    public static RetrievedVerificationEvent notFound()
    {
        return new RetrievedVerificationEvent(null, Status.NOT_FOUND);
    }
}
