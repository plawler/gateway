package org.inbloom.gateway.persistence.service;

import org.inbloom.gateway.core.event.provider.*;
import org.inbloom.gateway.persistence.domain.UserEntity;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public interface ApplicationProviderPersistenceService {

    public RegisteredApplicationProviderEvent createApplicationProvider(RegisterApplicationProviderEvent registerApplicationProviderEvent);
    public ModifiedApplicationProviderEvent modifyApplicationProvider(ModifyApplicationProviderEvent modifyApplicationProviderEvent);
    public RetrievedApplicationProviderEvent retrieveApplicationProvider(RetrieveApplicationProviderEvent retrieveApplicationProviderEvent);
    public UserEntity getUserByEmail(String email);

}
