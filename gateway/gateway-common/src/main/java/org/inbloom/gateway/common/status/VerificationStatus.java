package org.inbloom.gateway.common.status;

/**
 * Created by lloydengebretsen on 4/9/14.
 */
public enum VerificationStatus implements Status{
    SUCCESS("Success"),
    NOT_FOUND("The verification token was not found"),
    ERROR("Unexpected error"),
    EXPIRED("Token has already expired"),
    REDEEMED("Token has already been redeemed"),
    NOTIFICATION_FAILED("Verification notification could not be sent");

    VerificationStatus(String statusMessage) {
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
