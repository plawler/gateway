package org.inbloom.gateway.core.event.provider;

import org.inbloom.gateway.core.domain.ApplicationProvider;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.common.status.ApplicationProviderStatus;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class ModifiedApplicationProviderEvent implements ResponseEvent {

    private final ApplicationProviderStatus status;
    private final ApplicationProvider data;

    private ModifiedApplicationProviderEvent(ApplicationProvider data, ApplicationProviderStatus status) {
        this.data = data;
        this.status = status;
    }

    @Override
    public Status status() {
        return status;
    }

    public static ModifiedApplicationProviderEvent success(ApplicationProvider data){
        return new ModifiedApplicationProviderEvent(data, ApplicationProviderStatus.SUCCESS);
    }

    public static ModifiedApplicationProviderEvent notFound(){
        return new ModifiedApplicationProviderEvent(null, ApplicationProviderStatus.NOT_FOUND);
    }

}
