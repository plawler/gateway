package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/27/14.
 */
public class ModifiedOperatorEvent implements Event {

    private final Long id;
    private final Boolean updateSuccessful;
    private final Status status;

    private ModifiedOperatorEvent(Long id, final Boolean updateSuccessful, Status status) {
        this.id = id;
        this.updateSuccessful = updateSuccessful;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Boolean isUpdateSuccessful() {
        return updateSuccessful;
    }

    public static ModifiedOperatorEvent notFound(Long id) {
        return new ModifiedOperatorEvent(id, false, Status.NOT_FOUND);
    }

    public static ModifiedOperatorEvent success(Long id) {
        return new ModifiedOperatorEvent(id, true, Status.SUCCESS);
    }

    @Override
    public Status status() {
        return this.status;
    }

}
