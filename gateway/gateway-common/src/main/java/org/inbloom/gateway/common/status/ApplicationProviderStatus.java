package org.inbloom.gateway.common.status;

/**
 * Created by lloydengebretsen on 4/9/14.
 */
public enum ApplicationProviderStatus implements Status {

    SUCCESS("Success"),
    ACCOUNT_EXISTS("Account already exists"),
    NOT_FOUND("Application Provider not found"),
    ERROR("Unexpected error");

    ApplicationProviderStatus(String statusMessage) {
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
