package org.inbloom.gateway.core.service;

import org.inbloom.gateway.core.event.*;
import org.springframework.stereotype.Service;

/**
 * created by benjaminmorgan on 3/24/14
 */
@Service
public interface ApplicationProviderService {

    public RegisteredApplicationProviderEvent registerApplicationProvider(RegisterApplicationProviderEvent createAppProviderEvent);

    public ModifiedApplicationProviderEvent modifyApplicationProvicer(ModifyApplicationProviderEvent modifyAppProviderEvent);

    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveAppProviderEvent);

}
