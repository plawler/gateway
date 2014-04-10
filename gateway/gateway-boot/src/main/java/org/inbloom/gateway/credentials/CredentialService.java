package org.inbloom.gateway.credentials;

import org.inbloom.gateway.core.event.user.AuthenticateUserEvent;
import org.inbloom.gateway.core.event.user.CreateCredentialsEvent;
import org.inbloom.gateway.core.event.user.CreatedCredentialsEvent;

/**
 * Created By: paullawler
 */
public interface CredentialService {

    CreatedCredentialsEvent createCredentials(CreateCredentialsEvent event);
    void authenticate(AuthenticateUserEvent event);

}
