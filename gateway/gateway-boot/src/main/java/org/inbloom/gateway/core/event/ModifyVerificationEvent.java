package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.persistence.domain.BaseEntity;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class ModifyVerificationEvent implements RequestEvent {

    private Long verificationId;
    private Boolean verified;
    private String clientIpAddress;
    private String token;

    public ModifyVerificationEvent(Verification verification) {
        this.verificationId = verification.getVerificationId();
        this.verified = verification.getVerified();
        this.clientIpAddress = verification.getClientIpAddress();
        this.token = verification.getToken();
    }

    public Long getVerificationId() {
        return verificationId;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public String getToken() {
        return token;
    }

    public Boolean getVerified() {
        return verified;
    }
}
