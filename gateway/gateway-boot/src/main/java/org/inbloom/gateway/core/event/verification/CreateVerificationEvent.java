package org.inbloom.gateway.core.event.verification;

import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.domain.Verification;
import org.inbloom.gateway.core.event.RequestEvent;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class CreateVerificationEvent implements RequestEvent {

    private final Verification data;

    public CreateVerificationEvent(User user) {
        data = new Verification();
        data.setVerified(false);
        data.setUser(user);
        data.setUserId(user.getUserId());
    }

    public Verification getData(){
        return data;
    }

}
