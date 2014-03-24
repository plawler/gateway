package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Verification;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class ModifiedVerificationEvent implements ResponseEvent{

    private final Verification data;

    public ModifiedVerificationEvent(Verification data) {
        this.data = data;
    }

    @Override
    public Status status() {
        return null;
    }
}
