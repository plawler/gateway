package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.Token;
import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.domain.Verification;

import java.util.Date;

/**
 * Created by lloydengebretsen on 3/21/14.
 */
public class CreatedVerificationEvent implements ResponseEvent{

    private final Verification data;

    public CreatedVerificationEvent(Verification data){
        this.data = data;
    }

    public Verification getData(){
        return data;
    }

    @Override
    public Status status() {
        return null;
    }
}
