package org.inbloom.gateway.core.event.verification;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class RetrievedVerificationEvent extends BaseResponseEvent{

    private final Verification data;

    private RetrievedVerificationEvent(Verification data, Status status) {
        this.data = data;
        this.setStatus(new GatewayStatus(status, null));
    }

    public Verification getData() {
        return data;
    }

    public boolean notFound() {
        return Status.NOT_FOUND.equals(statusCode());
    }

    public boolean success() {
        return Status.SUCCESS.equals(statusCode());
    }

    public static RetrievedVerificationEvent success(Verification verification)
    {
        return new RetrievedVerificationEvent(verification, Status.SUCCESS);
    }

    public static RetrievedVerificationEvent newNotFound()
    {
        return new RetrievedVerificationEvent(null, Status.NOT_FOUND);
    }
}
