package org.inbloom.gateway.core.domain;

import java.util.Date;

/**
 * Created By: paullawler
 */
public class AccountValidation {

    private String validationToken;
    private String password;
    private Date validationDate;

    public AccountValidation(String validationToken, String password, Date validationDate) {
        this.validationToken = validationToken;
        this.password = password;
        this.validationDate = validationDate;
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
