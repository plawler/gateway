package org.inbloom.gateway.common.status;

/**
 * Created by lloydengebretsen on 4/9/14.
 */
public enum OperatorStatus implements Status{
    SUCCESS("Success"),
    NOT_FOUND("Operator not found"),
    ERROR("Unexpected error");

    OperatorStatus(String statusMessage) {
        this.statusMessage = statusMessage;
        this.statusCode = this.name();
    }

    private String statusMessage;
    private String statusCode;

    @Override
    public String getStatusCode() {
        return statusCode;
    }

    @Override
    public String getStatusMessage() {
        return statusMessage;
    }
}
