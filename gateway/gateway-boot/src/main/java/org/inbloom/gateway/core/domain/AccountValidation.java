package org.inbloom.gateway.core.domain;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * Created By: paullawler
 */
public class AccountValidation {

    @NotEmpty
    private String validationToken;

    @NotEmpty
    private String password;

    private Date validationDate;

    private AccountValidation() {}

    public AccountValidation(String validationToken, String password) {
        this.validationToken = validationToken;
        this.password = password;
        this.validationDate = new Date();
    }

    public String getValidationToken() {
        return validationToken;
    }

    private void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

    public String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public Date getValidationDate() {
        if (validationDate == null) {
            validationDate = new Date();
        }
        return validationDate;
    }

    private void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

}
