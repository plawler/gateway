package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.ApplicationProvider;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RetrievedApplicationProviderEvent implements ResponseEvent{

    private final ApplicationProvider data;
    private final Status status;

    private RetrievedApplicationProviderEvent(ApplicationProvider data, Status status) {
        this.data =data;
        this.status = status;
    }

    @Override
    public Status status() {
        return status;
    }

    public ApplicationProvider getData() {
        return data;
    }


    public static RetrievedApplicationProviderEvent success(ApplicationProvider data) {
        return new RetrievedApplicationProviderEvent(data, Status.SUCCESS);
    }

    public static RetrievedApplicationProviderEvent notFound() {
        return new RetrievedApplicationProviderEvent(null, Status.NOT_FOUND);
    }
}
