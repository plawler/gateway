package org.inbloom.gateway.core.event;

import org.inbloom.gateway.persistence.domain.BaseEntity;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class ModifyVerificationEvent implements RequestEvent{
    private Long verificationId;
    private Boolean verified;
    private String clientIpAddress;

    public Long getVerificationId() {
        return verificationId;
    }

    public Boolean isVerified() {
        return verified;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }
}
