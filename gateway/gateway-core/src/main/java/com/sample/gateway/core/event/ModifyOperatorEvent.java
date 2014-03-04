package com.sample.gateway.core.event;

import com.sample.gateway.core.domain.Operator;

import java.util.Date;

/**
 * Created by lloydengebretsen on 2/27/14.
 */
public class ModifyOperatorEvent {

    private final Long id;
    private final Operator data;

    public ModifyOperatorEvent(Long id, Operator data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getOperatorName() {
        return data.getOperatorName();
    }

    public String getApiUri() {
        return data.getApiUri();
    }

    public String getConnectorUri() {
        return data.getConnectorUri();
    }

    public Boolean isEnabled() {
        return data.isEnabled();
    }

    public String getPrimaryContactName() {
        return data.getPrimaryContactName();
    }

    public String getPrimaryContactEmail() {
        return data.getPrimaryContactEmail();
    }

    public String getPrimaryContactPhone() {
        return data.getPrimaryContactPhone();
    }

}