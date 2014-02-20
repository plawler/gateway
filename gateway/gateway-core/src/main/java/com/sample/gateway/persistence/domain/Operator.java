package com.sample.gateway.persistence.domain;

import com.sample.gateway.core.event.OperatorData;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by lloydengebretsen on 2/15/14.
 */
@Entity(name = "operators")
public class Operator extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long operatorId;

    private String operatorName;
    private String apiUri;
    private String connectorUri;
    private Date contractStartOn;
    private Date contractEndOn;

    @Column(name="is_enabled", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean enabled;

    public Operator(String createdBy){
        super(createdBy);
    }

    public Operator(){

    }

    public static Operator fromOperatorData(OperatorData data) {
        Operator operator = new Operator();
        BeanUtils.copyProperties(data, operator);
        return operator;
    }

    public OperatorData details() {
        OperatorData data = new OperatorData();
        BeanUtils.copyProperties(this, data);
        return data;
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

    public boolean isEnabled() { return enabled;}

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConnectorUri() { return connectorUri; }

    public void setConnectorUri(String connectorUri) { this.connectorUri = connectorUri; }

    public Date getContractStartOn() { return contractStartOn; }

    public void setContractStartOn(Date contractStartOn) { this.contractStartOn = contractStartOn; }

    public Date getContractEndOn() { return contractEndOn; }

    public void setContractEndOn(Date contractEndOn) { this.contractEndOn = contractEndOn; }


}