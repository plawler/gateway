package org.inbloom.gateway.core.event.verification;

import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.common.status.Status;
import org.inbloom.gateway.common.status.VerificationStatus;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class ModifiedVerificationEvent implements ResponseEvent {

    private final Verification data;
    private final VerificationStatus status;

    private ModifiedVerificationEvent(Verification data, VerificationStatus status) {
        this.data = data;
        this.status = status;
    }

    @Override
    public Status status() {
        return status;
    }

    public static ModifiedVerificationEvent success(Verification data){
        return new ModifiedVerificationEvent(data, VerificationStatus.SUCCESS);
    }

    public static ModifiedVerificationEvent notFound(){
        return new ModifiedVerificationEvent(null, VerificationStatus.NOT_FOUND);
    }

    public boolean successful() {
        return VerificationStatus.SUCCESS.equals(status());
    }
}
