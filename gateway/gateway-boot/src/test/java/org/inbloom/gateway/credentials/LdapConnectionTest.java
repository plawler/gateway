package org.inbloom.gateway.credentials;

import com.unboundid.ldap.sdk.*;
import com.unboundid.ldif.LDIFException;
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

    private static final String TEST_DN = "cn=sonny.corleone@mailinator.com,ou=people,ou=LocalNew,ou=DevTest,dc=slidev,dc=org";

    @Autowired
    private LDAPConnection connection;

    @Test
    public void shouldConnectToLdapServer() {
        assertNotNull(connection);
    }

    @Test
    public void shouldCrudEntries() throws LDAPException, LDIFException {
        LDAPResult added = connection.add(addRequest());
        assertEquals(0, added.getResultCode().intValue());

        SearchResult result = connection.search(searchRequest());
        assertEquals(1, result.getEntryCount());

        SearchResultEntry entry = result.getSearchEntries().get(0);

        assertEquals("sonny.corleone@mailinator.com", entry.getAttributeValue("cn"));
        assertEquals("sonny.corleone@mailinator.com", entry.getAttributeValue("mail"));
        assertEquals("Santino", entry.getAttributeValue("givenname"));
        assertEquals("Corleone", entry.getAttributeValue("sn"));
        assertEquals("s@ntin0rul3z", entry.getAttributeValue("userPassword"));

        LDAPResult modified = connection.modify(modifyRequest());
        assertEquals(0, modified.getResultCode().intValue());

        DeleteRequest request = new DeleteRequest(TEST_DN);
        LDAPResult deleted = connection.delete(request);
        assertEquals(0, deleted.getResultCode().intValue());
    }

    private String[] attributes() {
        return new String[] {"cn", "objectClass", "givenName", "sn", "uid", "userPassword", "displayName",
                "destinationIndicator", "homeDirectory", "uidNumber", "gidNumber", "mail", "loginShell", "gecos", "createdTimestamp", "modifyTimestamp"};
    }

    private ModifyRequest modifyRequest() throws LDIFException {
        return new ModifyRequest(
            "dn: " + TEST_DN,
            "changetype: modify",
            "replace: givenName",
            "givenName: Sonny"
        );
    }

    private AddRequest addRequest() {
        try {
            return new AddRequest(
                "dn: " + TEST_DN,
                "objectClass: inetOrgPerson",
                "objectClass: posixAccount",
                "objectClass: top",
                "cn: sonny.corleone@mailinator.com",
                "gidNumber: 10000",
                "homeDirectory: /dev/null",
                "givenName: Santino",
                "sn: Corleone",
                "uid: sonny.corleone@mailinator.com",
                "uidNumber: 10148",
                "displayName: -",
                "loginShell: /sbin/nologin",
                "mail: sonny.corleone@mailinator.com",
                "userPassword: s@ntin0rul3z"
            );
        } catch (LDIFException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Your add request is jacked up, Holmes.");
    }

    private SearchRequest searchRequest() throws LDAPException {
        return new SearchRequest("ou=people,ou=LocalNew,ou=DevTest,dc=slidev,dc=org", SearchScope.SUB,
                "mail=sonny.corleone@mailinator.com", attributes());
    }

}
