package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.ApplicationProvider;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RetrievedApplicationProviderEvent implements ResponseEvent{

    private final ApplicationProvider appProvider;
    private final Status status;

    private RetrievedApplicationProviderEvent(ApplicationProvider data, Status status) {
        this.appProvider=data;
        this.status = status;
    }

    @Override
    public Status status() {
        return null;
    }

    public ApplicationProvider getApplicationProvider() {
        return appProvider;
    }


}
