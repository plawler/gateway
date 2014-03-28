package org.inbloom.gateway.credentials;

import org.inbloom.gateway.core.event.AuthenticateUserEvent;
import org.inbloom.gateway.core.event.CreateCredentialsEvent;
import org.inbloom.gateway.core.event.CreatedCredentialsEvent;

/**
 * Created By: paullawler
 */
public interface CredentialService {

    CreatedCredentialsEvent createCredentials(CreateCredentialsEvent event);
    void authenticate(AuthenticateUserEvent event);

}
