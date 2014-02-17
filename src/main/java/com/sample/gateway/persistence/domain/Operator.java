package com.sample.gateway.persistence.domain;

import javax.persistence.*;

/**
 * Created by lloydengebretsen on 2/15/14.
 */
@Entity(name = "operators")
public class Operator extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operatorId;

    private String operatorName;
    private String apiUri;
    @Column(name="is_enabled", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean enabled;

    public Operator(String createdBy){
        super(createdBy);
    }

    public Operator(){

    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
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



}
