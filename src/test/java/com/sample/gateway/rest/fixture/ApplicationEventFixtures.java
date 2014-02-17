package com.sample.gateway.rest.fixture;

import com.sample.gateway.core.event.ApplicationData;
import com.sample.gateway.core.event.RegisteredApplicationEvent;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/17/14
 * Time: 1:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationEventFixtures {

    public static RegisteredApplicationEvent applicationRegistered(Long applicationId) {
        ApplicationData data = new ApplicationData();
        data.setApplicationId(applicationId);
        return new RegisteredApplicationEvent(data);
    }

}
