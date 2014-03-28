package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.ApplicationProvider;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RegisteredApplicationProviderEvent implements VerboseResponseEvent{

    private final ApplicationProvider data;
    private final Status status;
    private final String errorMessage;

    private RegisteredApplicationProviderEvent(ApplicationProvider data, Status status) {
        this.data = data;
        this.status = status;
        this.errorMessage = null;
    }

    private RegisteredApplicationProviderEvent(String errorMessage)
    {
        this.data = null;
        this.status = Status.FAILED;
        this.errorMessage = errorMessage;
    }

    public static RegisteredApplicationProviderEvent success(ApplicationProvider data){
        return new RegisteredApplicationProviderEvent(data, Status.SUCCESS);
    }

    public static RegisteredApplicationProviderEvent fail(String message) {
        return new RegisteredApplicationProviderEvent(message);
    }

    public ApplicationProvider getData() {
        return data;
    }

    @Override
    public Status status() {
        return status;
    }

    public ApplicationProvider getApplicationProvider() {
        return data;
    }

    @Override
    public String message() {
        return errorMessage;
    }
}
