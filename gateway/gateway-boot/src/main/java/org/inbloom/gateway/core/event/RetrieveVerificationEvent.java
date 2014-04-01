package org.inbloom.gateway.core.event;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class RetrieveVerificationEvent implements RequestEvent{

    private final String token;

    public RetrieveVerificationEvent(String token) {
        this.token=token;
    }

    public String getVerificationToken() {
        return token;
    }
}
