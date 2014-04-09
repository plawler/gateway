package org.inbloom.gateway.core.event.provider;

import org.inbloom.gateway.core.event.RequestEvent;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RetrieveApplicationProviderEvent implements RequestEvent {

    private final Long id;

    public RetrieveApplicationProviderEvent(Long id)
    {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
