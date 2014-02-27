package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Operator;

/**
 * Created by lloydengebretsen on 2/26/14.
 */
public class RetrieveOperatorEvent {

    private final Long id;

    public RetrieveOperatorEvent(Long id) {
        this.id = id;
    }

    public Long getId(){ return id; };
}
