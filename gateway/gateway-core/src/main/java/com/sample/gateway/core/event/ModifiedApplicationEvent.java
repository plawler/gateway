package com.sample.gateway.core.event;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/18/14
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModifiedApplicationEvent {

    private final ApplicationData data;

    public ModifiedApplicationEvent(ApplicationData data) {
        this.data = data;
    }

    public Long getApplicationId() {
        return data.getApplicationId();
    }

}
