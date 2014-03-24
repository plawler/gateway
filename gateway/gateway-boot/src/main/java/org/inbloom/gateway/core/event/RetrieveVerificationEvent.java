package org.inbloom.gateway.core.event;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class RetrieveVerificationEvent implements RequestEvent{
    private final Long verificationId;

    public RetrieveVerificationEvent(Long verificationId) {
        this.verificationId = verificationId;
    }

    public Long getVerificationId() {
        return verificationId;
    }
}
