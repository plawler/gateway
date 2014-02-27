package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/27/14.
 */
public class ModifiedOperatorEvent {

    private final Long id;
    private final Operator data;
    private final Boolean updateSuccessful;
    private final String statusMessage;

    public ModifiedOperatorEvent(Long id, Operator data, Boolean updateSuccessful, String statusMessage) {
        this.data = data;
        this.id = id;
        this.updateSuccessful = updateSuccessful;
        this.statusMessage = statusMessage;
    }

    public Operator getData() {
        return data;
    }

    public Long getId() {
        return id;
    }

    public Boolean isUpdateSuccessful() {
        return updateSuccessful;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}
