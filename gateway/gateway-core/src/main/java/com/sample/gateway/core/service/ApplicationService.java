package com.sample.gateway.core.service;

import com.sample.gateway.core.event.RegisterApplicationEvent;
import com.sample.gateway.core.event.RegisteredApplicationEvent;
import com.sample.gateway.core.event.RetrieveApplicationEvent;
import com.sample.gateway.core.event.RetrievedApplicationEvent;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 8:44 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationService {

    RegisteredApplicationEvent registerNewApplication(RegisterApplicationEvent registerApplicationEvent);
    RetrievedApplicationEvent retrieveApplication(RetrieveApplicationEvent retrieveApplicationEvent);

}
