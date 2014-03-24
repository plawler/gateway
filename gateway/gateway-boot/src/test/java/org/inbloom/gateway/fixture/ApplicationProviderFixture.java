package org.inbloom.gateway.fixture;

import org.inbloom.gateway.core.domain.ApplicationProvider;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class ApplicationProviderFixture {

    public static ApplicationProvider buildApplicationProvider(){
        ApplicationProvider applicationProvider = new ApplicationProvider();
        applicationProvider.setUser(UserFixture.buildUser());
        applicationProvider.setApplicationProviderName("Worlds greatest application provider");
        applicationProvider.setOrganizationName("#1 App Provider Inc.");
        return applicationProvider;
    }
}
