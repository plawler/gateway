package org.inbloom.gateway.credentials;

import com.unboundid.ldap.sdk.LDAPConnection;
import org.inbloom.gateway.core.event.AuthenticateUserEvent;
import org.inbloom.gateway.core.event.CreateCredentialsEvent;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created By: paullawler
 */
public class LdapCredentialService implements CredentialService {

    @Autowired
    LDAPConnection ldapConnection;

    @Override
    public void createCredentials(CreateCredentialsEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void authenticate(AuthenticateUserEvent event) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

}
