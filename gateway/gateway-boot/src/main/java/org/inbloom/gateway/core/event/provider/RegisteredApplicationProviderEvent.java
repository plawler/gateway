package org.inbloom.gateway.core.event.provider;

import org.inbloom.gateway.core.event.VerboseResponseEvent;
import org.inbloom.gateway.common.domain.ApplicationProvider;
import org.inbloom.gateway.common.status.ApplicationProviderStatus;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RegisteredApplicationProviderEvent implements VerboseResponseEvent {

    private final ApplicationProvider data;
    private final ApplicationProviderStatus status;
    private final String errorMessage;

    private RegisteredApplicationProviderEvent(ApplicationProvider data, ApplicationProviderStatus status, String errorMessage) {
        this.data = data;
        this.status = status;
        this.errorMessage = null;
    }

    public static RegisteredApplicationProviderEvent success(ApplicationProvider data){
        return new RegisteredApplicationProviderEvent(data, ApplicationProviderStatus.SUCCESS, null);
    }

    public static RegisteredApplicationProviderEvent fail(String message) {
        return new RegisteredApplicationProviderEvent(null, ApplicationProviderStatus.ERROR, message);
    }

    public static RegisteredApplicationProviderEvent accountExists(String message) {
        return new RegisteredApplicationProviderEvent(null, ApplicationProviderStatus.ACCOUNT_EXISTS, message);
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
