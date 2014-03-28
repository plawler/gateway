package org.inbloom.gateway.core.event;

/**
 * Created By: paullawler
 */
public class CreateCredentialsEvent implements RequestEvent {
    // todo: negotiate the necessary data to be exposed

    private String emailAddress;
    private String firstName;
    private String lastName;
    private String password;

    public CreateCredentialsEvent(String firstName, String lastName, String emailAddress, String password) {
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

}
