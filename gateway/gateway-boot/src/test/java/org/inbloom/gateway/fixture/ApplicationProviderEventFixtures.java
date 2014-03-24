package org.inbloom.gateway.fixture;

import org.inbloom.gateway.core.domain.ApplicationProvider;
import org.inbloom.gateway.core.event.ModifyApplicationProviderEvent;
import org.inbloom.gateway.core.event.RegisterApplicationProviderEvent;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class ApplicationProviderEventFixtures {

    public static RegisterApplicationProviderEvent buildRegisterApplicationProviderEvent(){
        return buildRegisterApplicationProviderEvent(ApplicationProviderFixture.buildApplicationProvider());
    }

    public static RegisterApplicationProviderEvent buildRegisterApplicationProviderEvent(ApplicationProvider applicationProvider){
        return new RegisterApplicationProviderEvent(applicationProvider);
    }

    public static ModifyApplicationProviderEvent buildModifyApplicationProviderEvent(Long appProviderId) {
        return buildModifyApplicationProviderEvent(appProviderId, ApplicationProviderFixture.modifyApplicationProvider(appProviderId));
    }

    public static ModifyApplicationProviderEvent buildModifyApplicationProviderEvent(Long appProviderId, ApplicationProvider applicationProvider) {
        return new ModifyApplicationProviderEvent(appProviderId, applicationProvider);
    }
}
