package org.inbloom.gateway.core.event;

import org.inbloom.gateway.core.domain.AccountValidation;

import java.util.Date;

/**
 * Created By: paullawler
 */
public class ValidateAccountSetupEvent {

    private final String validationToken;
    private final String password;
    private final Date validationDate;

    public ValidateAccountSetupEvent(AccountValidation validation) {
        this.validationToken = validation.getValidationToken();
        this.password = validation.getPassword();
        this.validationDate = validation.getValidationDate();
    }

    public String getValidationToken() {
        return validationToken;
    }

    public String getPassword() {
        return password;
    }

    public Date getValidationDate() {
        return validationDate;
    }

}
