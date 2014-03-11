package org.inbloom.gateway.core.event;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class RetrieveOperatorEvent implements RequestEvent{

    private final Long id;

    public RetrieveOperatorEvent(Long id) {
        this.id = id;
    }

    public Long getId(){ return id; };
}
