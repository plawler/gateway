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

    public CreateVerificationEvent(User user, Token token, Date validFrom, Date validUntil) {
        data = new Verification();
        data.setToken(token);
        data.setUser(user);
        data.setValidFrom(validFrom);
        data.setValidUntil(validUntil);
        data.setVerified(false);
    }

    public Verification getData(){
        return data;
    }
}
