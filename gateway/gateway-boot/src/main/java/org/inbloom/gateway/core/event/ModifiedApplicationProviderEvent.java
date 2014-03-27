package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.ApplicationProvider;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class ModifiedApplicationProviderEvent implements ResponseEvent{

    private final Status status;
    private final ApplicationProvider data;

    private ModifiedApplicationProviderEvent(ApplicationProvider data, Status status) {
        this.data = data;
        this.status = status;
    }

    @Override
    public Status status() {
        return status;
    }

    public static ModifiedApplicationProviderEvent success(ApplicationProvider data){
        return new ModifiedApplicationProviderEvent(data, Status.SUCCESS);
    }

    public static ModifiedApplicationProviderEvent notFound(){
        return new ModifiedApplicationProviderEvent(null, Status.NOT_FOUND);
    }

}
