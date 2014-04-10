package org.inbloom.portal.forms;

/**
 * @author benjaminmorgan
 *         Date: 4/2/14
 */
public class SignupCompletion {


    private String validationToken;

    private String password;

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
}
