package com.sample.gateway.core.event;

/**
 * Created by lloydengebretsen on 2/18/14.
 */
public class RetrieveApplicationEvent {

    private final Long applicationId;

    public RetrieveApplicationEvent(Long applicationId){
        this.applicationId = applicationId;
    }


    public Long getApplicationId() {
        return applicationId;
    }

}
