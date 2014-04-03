package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Verification;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class RetrieveVerificationEvent implements RequestEvent{

    private final String token;
    private final Long verificationId;

    public RetrieveVerificationEvent(Long verificationId, String token) {
        this.token=token;
        this.verificationId = verificationId;
    }

    public String getVerificationToken() {
        return token;
    }

    public Long getVerificationId() {
        return verificationId;
    }
}
