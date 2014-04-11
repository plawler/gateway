package org.inbloom.gateway.core.event.verification;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class ModifiedVerificationEvent extends BaseResponseEvent {

    private final Verification data;

    private ModifiedVerificationEvent(Verification data, Status status) {
        this.data = data;
        this.setStatus(new GatewayStatus(status, null));
    }

    public static ModifiedVerificationEvent success(Verification data){
        return new ModifiedVerificationEvent(data, Status.SUCCESS);
    }

    public static ModifiedVerificationEvent notFound(){
        return new ModifiedVerificationEvent(null, Status.NOT_FOUND);
    }

    public boolean successful() {
        return Status.SUCCESS.equals(statusCode());
    }
}
