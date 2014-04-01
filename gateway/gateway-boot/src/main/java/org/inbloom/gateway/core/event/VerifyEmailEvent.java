package org.inbloom.gateway.core.event;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class VerifyEmailEvent implements RequestEvent{

    private String verificationToken;
    private String clientIpAddress;

    public VerifyEmailEvent(String token, String clientIpAddress) {
        this.verificationToken=token;
        this.clientIpAddress=clientIpAddress;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public String getClientIpAddress() {
        return clientIpAddress;
    }
}
