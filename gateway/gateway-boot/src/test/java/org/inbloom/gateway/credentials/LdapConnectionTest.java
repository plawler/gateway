package org.inbloom.gateway.credentials;

import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPSearchException;
import com.unboundid.ldap.sdk.SearchResult;
import com.unboundid.ldap.sdk.SearchScope;
import org.inbloom.gateway.configuration.LdapConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created By: paullawler
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LdapConfiguration.class)
public class LdapConnectionTest {

    @Autowired
    private LDAPConnection connection;

    @Test
    public void shouldConnectToLdapServer() {
        assertNotNull(connection);
    }

    @Test
    public void shouldRetrieveFromLdapServer() throws LDAPSearchException {
        SearchResult result = connection.search("ou=LocalNew,ou=DevTest,dc=slidev,dc=org", SearchScope.SUB, "(mail=testdeveloper@slidev.org)", "mail");
        assertEquals(1, result.getEntryCount());
    }

}
