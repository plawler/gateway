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

    public AccountValidation(String validationToken, String password) {
        this.validationToken = validationToken;
        this.password = password;
        this.validationDate = new Date();
    }

    public String getValidationToken() {
        return validationToken;
    }

    public void setValidationToken(String validationToken) {
        this.validationToken = validationToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getValidationDate() {
        return validationDate;
    }

    public void setValidationDate(Date validationDate) {
        this.validationDate = validationDate;
    }

}
