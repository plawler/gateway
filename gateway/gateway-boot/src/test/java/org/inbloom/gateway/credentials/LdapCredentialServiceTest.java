package org.inbloom.gateway.credentials;

import com.unboundid.ldap.sdk.AddRequest;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.ModifyRequest;
import com.unboundid.ldif.LDIFException;
import org.inbloom.gateway.core.event.CreateCredentialsEvent;
import org.inbloom.gateway.core.event.ResponseEvent;
import org.inbloom.gateway.credentials.ldap.LdapService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        ResponseEvent response = service.createCredentials(sonnysCredentialsEvent());

        verify(ldap).add(any(AddRequest.class));
        verify(ldap, times(2)).modify(any(ModifyRequest.class));

        assertEquals(ResponseEvent.Status.SUCCESS, response.status());
    }

    @Test(expected = Exception.class)
    public void shouldFailOnMalformedRequest() throws LDAPException {
        ResponseEvent response = service.createCredentials(malformedCredentialsEvent());
        verify(ldap).add(any(AddRequest.class));
        verify(ldap, times(2)).modify(any(ModifyRequest.class));
        assertEquals(ResponseEvent.Status.FAILED, response.status());
    }

    private CreateCredentialsEvent malformedCredentialsEvent() {
        return new CreateCredentialsEvent("Santino", "Corleone", null, "s@ntin0rul3z");
    }

    private CreateCredentialsEvent sonnysCredentialsEvent() {
        return new CreateCredentialsEvent("Santino", "Corleone", "sonny.corleone@mailinator.com", "s@ntin0rul3z");
    }

}
