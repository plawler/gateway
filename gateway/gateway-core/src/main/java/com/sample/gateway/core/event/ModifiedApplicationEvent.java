package com.sample.gateway.core.event;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/18/14
 * Time: 3:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModifiedApplicationEvent {

    private final Long applicationId;
    private boolean updateCompleted;
    private ApplicationData data;

    private ModifiedApplicationEvent(Long applicationId) {
        this.applicationId = applicationId;
        this.updateCompleted = true;
    }

    public static ModifiedApplicationEvent newInstance(Long applicationId, ApplicationData data) {
        ModifiedApplicationEvent event = new ModifiedApplicationEvent(applicationId);
        event.updateCompleted = true;
        event.data = data;
        return event;
    }

    public Long getApplicationId() {
        return data.getApplicationId();
    }

    public static ModifiedApplicationEvent notFound(Long applicationId) {
        // todo: any details for the modification event that we want to log, message, etc.?
        ModifiedApplicationEvent event = new ModifiedApplicationEvent(applicationId);
        event.updateCompleted = false;
        return event;
    }

    public boolean updateCompleted() {
        return this.updateCompleted;
    }
}
