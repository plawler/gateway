package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.RegisterApplicationEvent;
import com.sample.gateway.core.event.RegisteredApplicationEvent;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationPersistenceService {

    RegisteredApplicationEvent registerApplication(RegisterApplicationEvent registerApplication);

}
