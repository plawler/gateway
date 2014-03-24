package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.core.event.*;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public interface ApplicationProviderPersistenceService {

    public RegisteredApplicationProviderEvent createApplicationProvider(RegisterApplicationProviderEvent registerApplicationProviderEvent);
    public ModifiedApplicationProviderEvent modifyApplicationProvider(ModifyApplicationProviderEvent modifyApplicationProviderEvent);
    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveApplicationProviderEvent);
}
