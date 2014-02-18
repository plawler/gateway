package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.RegisterApplicationProviderEvent;
import com.sample.gateway.core.event.RegisteredApplicationProviderEvent;
import com.sample.gateway.core.event.RetrieveApplicationProviderEvent;
import com.sample.gateway.core.event.RetrievedApplicationProviderEvent;

/**
 * Created by lloydengebretsen on 2/18/14.
 */
public interface ApplicationProviderPersistenceService {

    RegisteredApplicationProviderEvent registerApplicationProvider(RegisterApplicationProviderEvent registerApplicationProviderEvent);

    RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveApplicationProviderEvent);
}
