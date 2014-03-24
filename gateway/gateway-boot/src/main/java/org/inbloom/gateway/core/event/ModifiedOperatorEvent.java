package org.inbloom.gateway.core.event;


import org.inbloom.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/27/14.
 */
public class ModifiedOperatorEvent implements ResponseEvent {

    private final Long id;
    private final Operator data;
    private final Status status;

    private ModifiedOperatorEvent(Long id, Operator data, Status status) {
        this.data = data;
        this.id = id;
        this.status = status;
    }

    public Operator getData() {
        return data;
    }

    public Long getId() {
        return id;
    }

    public static ModifiedOperatorEvent notFound(Long id)
    {
        return new ModifiedOperatorEvent(id, null, Status.NOT_FOUND);
    }

    public static ModifiedOperatorEvent success(Long id, Operator modified)
    {
        return new ModifiedOperatorEvent(id, modified, Status.SUCCESS);
    }

    @Override
    public Status status() {
        return this.status;
    }
}
