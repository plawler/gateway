package org.inbloom.gateway.rest.validation;

/**
 * @author benjaminmorgan
 */
public enum GatewayError {

    USER_ALREADY_REGISTERED("A user with that email has already registered for an account");

    private String message;

    private GatewayError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
