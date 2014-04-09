package org.inbloom.gateway.credentials;

import com.unboundid.ldap.sdk.LDAPException;
import org.inbloom.gateway.core.event.AuthenticateUserEvent;
import org.inbloom.gateway.core.event.CreateCredentialsEvent;
import org.inbloom.gateway.core.event.CreatedCredentialsEvent;
import org.inbloom.gateway.credentials.ldap.LdapRequestFactory;
import org.inbloom.gateway.credentials.ldap.LdapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created By: paullawler
 */
@Service
public class CredentialServiceImpl implements CredentialService {

    private static final Logger logger = LoggerFactory.getLogger(CredentialServiceImpl.class);

    private final LdapService ldapService;

    @Autowired
    public CredentialServiceImpl(LdapService ldapService) {
        this.ldapService = ldapService;
    }

    @Override
    public CreatedCredentialsEvent createCredentials(CreateCredentialsEvent event) {
        try {
            ldapService.add(LdapRequestFactory.newPersonRequest(event.getFirstName(), event.getLastName(),
                    event.getEmailAddress(), event.getPassword()));
            ldapService.modify(LdapRequestFactory.newAddToAppDeveloperGroupRequest(event.getEmailAddress()));
            ldapService.modify(LdapRequestFactory.newAddToSandboxAdminRequest(event.getEmailAddress()));
        } catch (LDAPException e) {
            logger.error("Adding credentials failed:" + e.getExceptionMessage());
            return CreatedCredentialsEvent.failed("An LDAPException was thrown most likely due to a malformed request");
        }
        return CreatedCredentialsEvent.success();
    }

    @Override
    public void authenticate(AuthenticateUserEvent event) {
        throw new UnsupportedOperationException("When SimpleIDP goes away, implement me!");
    }

}
