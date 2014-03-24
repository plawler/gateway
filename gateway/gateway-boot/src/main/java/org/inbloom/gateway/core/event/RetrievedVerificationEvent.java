package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Verification;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class RetrievedVerificationEvent {

    private final Verification data;

    public RetrievedVerificationEvent(Verification data) {
        this.data = data;
    }

    public Verification getData() {
        return data;
    }
}
