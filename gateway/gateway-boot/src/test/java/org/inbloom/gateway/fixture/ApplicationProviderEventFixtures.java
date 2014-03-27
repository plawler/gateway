package org.inbloom.gateway.fixture;

import org.inbloom.gateway.core.event.*;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class ApplicationProviderEventFixtures {

    /**register**/
    public static RegisterApplicationProviderEvent buildRegisterAppProviderEvent(Long id) {
        return new RegisterApplicationProviderEvent(ApplicationProviderFixture.buildAppProvider1(id));
    }

    /**modify**/
    public static ModifyApplicationProviderEvent buildModifyAppProviderEvent(Long id) {
        return new ModifyApplicationProviderEvent(id, ApplicationProviderFixture.buildAppProvider2(id));
    }

    /**retrieve**/
    public static RetrieveApplicationProviderEvent buildRetrieveAppProviderEvent(Long id) {
        return new RetrieveApplicationProviderEvent(id);
    }

    /**registered**/
    public static RegisteredApplicationProviderEvent buildSuccessRegisteredAppProviderEvent(Long id){
        return RegisteredApplicationProviderEvent.success(ApplicationProviderFixture.buildAppProvider1(id));
    }

    public static RegisteredApplicationProviderEvent buildFailRegisteredAppProviderEvent() {
        return RegisteredApplicationProviderEvent.fail("fail");
    }

    /**modified**/
    public static ModifiedApplicationProviderEvent buildSuccessModifiedAppProviderEvent(Long id) {
        return ModifiedApplicationProviderEvent.success(ApplicationProviderFixture.buildAppProvider2(id));
    }

    public static ModifiedApplicationProviderEvent buildNotFoundModifiedApplicationProviderEvent() {
        return ModifiedApplicationProviderEvent.notFound();
    }

    /**retrieved**/
    public static RetrievedApplicationProviderEvent buildSuccessRetrievedAppProviderEvent(Long id) {
        return RetrievedApplicationProviderEvent.success(ApplicationProviderFixture.buildAppProvider2(id));
    }

    public static RetrievedApplicationProviderEvent buildNotFoundRetrievedAppProviderEvent() {
        return RetrievedApplicationProviderEvent.notFound();
    }


}
