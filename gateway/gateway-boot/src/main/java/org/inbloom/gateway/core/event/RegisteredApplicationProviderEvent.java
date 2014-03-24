package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.ApplicationProvider;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RegisteredApplicationProviderEvent implements ResponseEvent{

    private final ApplicationProvider data;
    private final Status status;

    private RegisteredApplicationProviderEvent(ApplicationProvider data, Status status) {
        this.data = data;
        this.status = status;
    }

    public static RegisteredApplicationProviderEvent success(ApplicationProvider data){
        return new RegisteredApplicationProviderEvent(data, Status.SUCCESS);
    }

    public ApplicationProvider getData(){
        return data;
    }

    @Override
    public Status status() {
        return status;
    }

    public ApplicationProvider getApplicationProvider() {
        return data;
    }
}
