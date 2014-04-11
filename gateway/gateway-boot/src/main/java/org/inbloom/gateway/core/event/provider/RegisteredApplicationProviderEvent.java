package org.inbloom.gateway.core.event.provider;

import org.inbloom.gateway.common.status.GatewayStatus;
import org.inbloom.gateway.core.event.BaseResponseEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.core.event.VerboseResponseEvent;
import org.inbloom.gateway.common.domain.ApplicationProvider;
import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class RegisteredApplicationProviderEvent extends BaseResponseEvent{

    private final ApplicationProvider data;

    private RegisteredApplicationProviderEvent(ApplicationProvider data, Status status, String errorMessage) {
        this.setStatus(new GatewayStatus(status, errorMessage));
        this.data = data;
    }

    public static RegisteredApplicationProviderEvent success(ApplicationProvider data){
        return new RegisteredApplicationProviderEvent(data, Status.SUCCESS, null);
    }

    public static RegisteredApplicationProviderEvent fail(String message) {
        return new RegisteredApplicationProviderEvent(null, Status.ERROR, message);
    }

    public static RegisteredApplicationProviderEvent accountExists(String message) {
        return new RegisteredApplicationProviderEvent(null, Status.CONFLICT, message);
    }

    public ApplicationProvider getData() {
        return data;
    }

    public ApplicationProvider getApplicationProvider() {
        return data;
    }

}
