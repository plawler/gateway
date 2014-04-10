package org.inbloom.gateway.common.status.rest;

import org.inbloom.gateway.common.status.Status;

/**
 * Created by lloydengebretsen on 4/9/14.
 */
public class StatusResponse {

    private final String statusMessage;
    private final String statusCode;

    public StatusResponse(Status status){
        this.statusMessage = status.getStatusMessage();
        this.statusCode = status.getStatusCode();
    }


    public String getStatusMessage() {
        return statusMessage;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
