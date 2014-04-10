package org.inbloom.gateway.core.event.provider;

import org.inbloom.gateway.core.domain.ApplicationProvider;
import org.inbloom.gateway.core.event.RequestEvent;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RegisterApplicationProviderEvent implements RequestEvent {

    private final ApplicationProvider data;

    public RegisterApplicationProviderEvent(ApplicationProvider data) {
        this.data = data;
    }

    public ApplicationProvider getData() {
        return data;
    }
}
