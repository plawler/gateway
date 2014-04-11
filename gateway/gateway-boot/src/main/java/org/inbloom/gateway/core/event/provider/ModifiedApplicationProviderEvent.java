package org.inbloom.gateway.core.event.provider;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.domain.ApplicationProvider;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class ModifiedApplicationProviderEvent extends BaseResponseEvent {

    private final ApplicationProvider data;

    private ModifiedApplicationProviderEvent(ApplicationProvider data, Status status) {
        this.data = data;
        this.setStatus(new GatewayStatus(status, null));
    }

    public static ModifiedApplicationProviderEvent success(ApplicationProvider data){
        return new ModifiedApplicationProviderEvent(data, Status.SUCCESS);
    }

    public static ModifiedApplicationProviderEvent notFound(){
        return new ModifiedApplicationProviderEvent(null, Status.NOT_FOUND);
    }

}
