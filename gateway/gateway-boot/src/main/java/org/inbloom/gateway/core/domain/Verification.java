package org.inbloom.gateway.core.domain;

import java.util.Date;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class Verification {
    private Long verificationId;

    private Boolean verified;
    private Date validFrom;
    private Date validUntil;
    private String clientIpAddress;
    private String token;
    private User user;

    public Long getVerificationId() {
        return verificationId;
    }

    public void setVerificationId(Long verificationId) {
        this.verificationId = verificationId;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }

    public void setClientIpAddress(String clientIpAddress) {
        this.clientIpAddress = clientIpAddress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
