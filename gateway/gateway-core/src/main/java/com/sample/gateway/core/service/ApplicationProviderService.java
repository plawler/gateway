package com.sample.gateway.core.service;

import com.sample.gateway.core.event.RegisterApplicationProviderEvent;
import com.sample.gateway.core.event.RegisteredApplicationProviderEvent;

/**
 * Created by lloydengebretsen on 2/18/14.
 */
public interface ApplicationProviderService {

    RegisteredApplicationProviderEvent registerApplicationProvider(RegisterApplicationProviderEvent registerApplicationProviderEvent);

}
