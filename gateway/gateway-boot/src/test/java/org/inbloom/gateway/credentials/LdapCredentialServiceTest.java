package org.inbloom.gateway.credentials;

import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldif.LDIFException;
import org.inbloom.gateway.Gateway;
import org.inbloom.gateway.configuration.LdapConfiguration;
import org.inbloom.gateway.core.event.CreateCredentialsEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.core.event.VerboseResponseEvent;
import org.inbloom.gateway.credentials.ldap.LdapEntryFactory;
import org.inbloom.gateway.credentials.ldap.LdapService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created By: paullawler
 */
@RunWith(MockitoJUnitRunner.class)
public class LdapCredentialServiceTest {

    @Mock
    private LdapService ldap;

    private CredentialServiceImpl service;

    @Before
    public void setUp() {
        service = new CredentialServiceImpl(ldap);
    }

    @Test
    public void shouldCreateCredentials() throws LDAPException, LDIFException {
        CreateCredentialsEvent command = new CreateCredentialsEvent("sonny.corleone@mailinator.com", "Santino", "Corleone", "s@ntin0rul3z");
        VerboseResponseEvent response = service.createCredentials(command);
        verify(ldap).addEntry(LdapEntryFactory.newPersonEntry(command));
//        verify(ldap).addEntry(LdapEntryFactory.newGroupEntry(command));

        assertEquals(ResponseEvent.Status.SUCCESS, response.status());
    }

}
