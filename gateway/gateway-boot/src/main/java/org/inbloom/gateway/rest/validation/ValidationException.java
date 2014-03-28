package org.inbloom.gateway.rest.validation;

/**
 * @author benjaminmorgan
 */
public class ValidationException extends IllegalStateException {

    GatewayError error;

    public ValidationException(GatewayError error)
    {
       super(error.getMessage());
        this.error=error;
    }

    public GatewayError getGatewayError()
    {
        return error;
    }

}
