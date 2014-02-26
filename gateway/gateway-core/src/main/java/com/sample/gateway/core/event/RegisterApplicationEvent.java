package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Application;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 8:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class RegisterApplicationEvent {

    private final Application application;

    public RegisterApplicationEvent(Application application) {
        this.application = application;
    }

    public Application getData() {
        return this.application;
    }

    public Long getApplicationProviderId() {
        return this.application.getApplicationProviderId();
    }

}
