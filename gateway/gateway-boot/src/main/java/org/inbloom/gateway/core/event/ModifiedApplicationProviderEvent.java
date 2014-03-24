package org.inbloom.gateway.core.event;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class ModifiedApplicationProviderEvent implements ResponseEvent{

    @Override
    public Status status() {
        return null;
    }

    public boolean isUpdateSuccessful() {
        return false;  //To change body of created methods use File | Settings | File Templates.
    }
}
