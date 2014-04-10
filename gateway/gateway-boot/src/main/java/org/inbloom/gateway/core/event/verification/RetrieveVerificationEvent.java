package org.inbloom.gateway.core.event.verification;

import org.inbloom.gateway.common.domain.Verification;
import org.inbloom.gateway.core.event.RequestEvent;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class RetrieveVerificationEvent implements RequestEvent {

    private final String token;
    private final Long verificationId;


    public RetrieveVerificationEvent(String token) {
        this.token = token;
        this.verificationId = null;
    }

    public String getVerificationToken() {
        return token;
    }

    public Long getVerificationId() {
        return verificationId;
    }

}
