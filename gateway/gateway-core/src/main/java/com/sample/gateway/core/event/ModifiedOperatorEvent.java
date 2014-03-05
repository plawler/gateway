package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/27/14.
 */
public class ModifiedOperatorEvent {

    public static final String SUCCESS = "Update Successful";
    public static final String NOT_FOUND = "Update failed, id not found";

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

    public static ModifiedOperatorEvent notFound(Long id)
    {
        return new ModifiedOperatorEvent(id, null, false, NOT_FOUND);
    }

    public static ModifiedOperatorEvent success(Long id, Operator modified)
    {
        return new ModifiedOperatorEvent(id, modified, true, SUCCESS);
    }

}
