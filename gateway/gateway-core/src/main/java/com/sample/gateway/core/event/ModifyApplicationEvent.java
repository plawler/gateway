package com.sample.gateway.core.event;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/18/14
 * Time: 3:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModifyApplicationEvent {

    private final ApplicationData data;

    public ModifyApplicationEvent(ApplicationData data) {
        this.data = data;
    }

    public Long getApplicationId() {
        return data.getApplicationId();
    }

    public String getApplicationName() {
        return data.getApplicationName();
    }

    public String getDescription() {
        return data.getDescription();
    }

    public String getAppUri() {
        return data.getAppUri();
    }

    public String getRedirectUri() {
        return data.getRedirectUri();
    }

    public String getImageUri() {
        return data.getImageUri();
    }

    public Boolean isAdmin() {
        return data.getAdmin();
    }

    public Boolean isBulkExtract() {
        return data.getBulkExtract();
    }

}
