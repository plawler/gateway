package org.inbloom.gateway.core.event.provider;

import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.ApplicationProvider;
import org.inbloom.gateway.common.status.ApplicationProviderStatus;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RetrievedApplicationProviderEvent implements ResponseEvent {

    private final ApplicationProvider data;
    private final ApplicationProviderStatus status;

    private RetrievedApplicationProviderEvent(ApplicationProvider data, ApplicationProviderStatus status) {
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
        return new RetrievedApplicationProviderEvent(data, ApplicationProviderStatus.SUCCESS);
    }

    public static RetrievedApplicationProviderEvent notFound() {
        return new RetrievedApplicationProviderEvent(null, ApplicationProviderStatus.NOT_FOUND);
    }
}
