package org.inbloom.gateway.core.event.provider;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.ApplicationProvider;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RetrievedApplicationProviderEvent extends BaseResponseEvent{

    private final ApplicationProvider data;

    private RetrievedApplicationProviderEvent(ApplicationProvider data, Status status) {
        this.data =data;
        this.setStatus(new GatewayStatus(status, null));
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
