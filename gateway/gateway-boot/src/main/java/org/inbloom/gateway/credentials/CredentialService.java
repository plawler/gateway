package org.inbloom.gateway.credentials;

import org.inbloom.gateway.core.event.AuthenticateUserEvent;
import org.inbloom.gateway.core.event.CreateCredentialsEvent;

/**
 * Created By: paullawler
 */
public interface CredentialService {

    void createCredentials(CreateCredentialsEvent event);
    void authenticate(AuthenticateUserEvent event);

}
