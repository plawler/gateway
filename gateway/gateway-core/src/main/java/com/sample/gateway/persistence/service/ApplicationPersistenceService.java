package com.sample.gateway.persistence.service;

import com.sample.gateway.core.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/16/14
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationPersistenceService {

    RegisteredApplicationEvent registerApplication(RegisterApplicationEvent registerApplication);

    RetrievedApplicationEvent retrieveApplication(RetrieveApplicationEvent retrieveApplication);

    ModifiedApplicationEvent modifyApplication(ModifyApplicationEvent modifyApplicationEvent);

}
