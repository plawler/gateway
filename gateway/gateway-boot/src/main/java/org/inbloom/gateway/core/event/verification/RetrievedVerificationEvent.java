package org.inbloom.gateway.core.event.verification;

import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.status.Status;
import org.inbloom.gateway.common.status.VerificationStatus;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class RetrievedVerificationEvent implements ResponseEvent {

    private final Verification data;
    private final VerificationStatus status;

    private RetrievedVerificationEvent(Verification data, VerificationStatus status) {
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

    public boolean notFound() {
        return VerificationStatus.NOT_FOUND.equals(status());
    }

    public boolean success() {
        return VerificationStatus.SUCCESS.equals(status());
    }

    public static RetrievedVerificationEvent success(Verification verification)
    {
        return new RetrievedVerificationEvent(verification, VerificationStatus.SUCCESS);
    }

    public static RetrievedVerificationEvent newNotFound()
    {
        return new RetrievedVerificationEvent(null, VerificationStatus.NOT_FOUND);
    }
}
