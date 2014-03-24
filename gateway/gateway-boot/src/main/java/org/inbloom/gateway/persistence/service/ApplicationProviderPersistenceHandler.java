package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.core.event.*;
import org.springframework.stereotype.Service;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
@Service
public class ApplicationProviderPersistenceHandler implements ApplicationProviderPersistenceService {


    @Override
    public CreatedApplicationProviderEvent createApplicationProvider(CreateApplicationProviderEvent createApplicationProviderEvent) {
        return null;
    }

    @Override
    public ModifiedApplicationProviderEvent modifyApplicationProvider(ModifyApplicationProviderEvent modifyApplicationProviderEvent) {
        return null;
    }

    @Override
    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveApplicationProviderEvent) {
        return null;
    }
}
