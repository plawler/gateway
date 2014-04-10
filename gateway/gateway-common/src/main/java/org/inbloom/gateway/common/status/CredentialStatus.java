package org.inbloom.gateway.common.status;

/**
 * Created by lloydengebretsen on 4/9/14.
 */
public enum CredentialStatus implements Status {

    ERROR("Unexpected Error"), SUCCESS("Success");

    private String statusMessage;
    private String statusCode;

     CredentialStatus(String statusMessage) {
         this.statusMessage = statusMessage;
    }


    public String getStatusMessage() {
        return statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
