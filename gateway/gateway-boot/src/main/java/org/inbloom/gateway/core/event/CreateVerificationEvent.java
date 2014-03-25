package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Token;
import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.domain.Verification;

import java.util.Date;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class CreateVerificationEvent implements RequestEvent{

    private final Verification data;
    private final Long userId;

    public CreateVerificationEvent(Long userId, Date validFrom, Date validUntil) {
        data = new Verification();
        data.setValidFrom(validFrom);
        data.setValidUntil(validUntil);
        data.setVerified(false);
        this.userId = userId;
    }

    public Verification getData(){
        return data;
    }

    public Long getUserId() {
        return userId;
    }
}
