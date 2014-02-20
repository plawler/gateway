package com.sample.gateway.core.domain;

import com.sample.gateway.core.event.OperatorData;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * Created by lloydengebretsen on 2/20/14.
 */
public class Operator {

    private Long operatorId;
    private String operatorName;
    private String apiUri;
    private String connectorUri;
    private Date contractStartOn;
    private Date contractEndOn;
    private boolean enabled;


    public OperatorData details(){
        OperatorData oData = new OperatorData();
        BeanUtils.copyProperties(this, oData);
        return oData;
    }

    public static Operator fromApplicationProviderData(OperatorData data) {
        Operator op = new Operator();
        BeanUtils.copyProperties(data, op);
        return op;
    }



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
