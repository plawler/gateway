package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.ApplicationProvider;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RegisterApplicationProviderEvent implements RequestEvent{

    private final ApplicationProvider data;

    public RegisterApplicationProviderEvent(ApplicationProvider data) {
        this.data = data;
    }

    public ApplicationProvider getData() {
        return data;
    }
}
