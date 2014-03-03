package com.sample.gateway.core.domain;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public class Operator {

    private Long operatorId;

    @NotEmpty
    private String operatorName;

    @URL
    @NotEmpty
    @Length(max = 128)
    private String apiUri;

    @URL
    @NotEmpty
    @Length(max = 128)
    private String connectorUri;

    private Date contractStartOn;
    private Date contractEndOn;

    @NotNull
    private boolean enabled;


    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getApiUri() {
        return apiUri;
    }

    public void setApiUri(String apiUri) {
        this.apiUri = apiUri;
    }

    public String getConnectorUri() {
        return connectorUri;
    }

    public void setConnectorUri(String connectorUri) {
        this.connectorUri = connectorUri;
    }

    public Date getContractStartOn() {
        return contractStartOn;
    }

    public void setContractStartOn(Date contractStartOn) {
        this.contractStartOn = contractStartOn;
    }

    public Date getContractEndOn() {
        return contractEndOn;
    }

    public void setContractEndOn(Date contractEndOn) {
        this.contractEndOn = contractEndOn;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
