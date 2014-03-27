package org.inbloom.gateway.credentials.ldap;

import com.unboundid.ldap.sdk.Attribute;
import com.unboundid.ldap.sdk.DN;
import com.unboundid.ldap.sdk.Entry;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldif.LDIFException;
import org.inbloom.gateway.core.event.CreateCredentialsEvent;

import java.util.Map;

/**
 * Created By: paullawler
 */
public class LdapEntryFactory {

    private static final String PEOPLE_BASE_DN = "ou=people,ou=LocalNew,ou=DevTest,dc=slidev,dc=org";

    public static Entry newPersonEntry(CreateCredentialsEvent event) throws LDIFException, LDAPException {
        DN dn = new DN("cn=" + event.getEmailAddress() + "," + PEOPLE_BASE_DN);
        Entry entry = new Entry(dn);

        entry.addAttribute("givenName", event.getFirstName());
        entry.addAttribute("sn", event.getLastName());
        entry.addAttribute("mail", event.getEmailAddress());
        entry.addAttribute("userPassword", event.getPassword());

        // standard stuff
        entry.addAttribute("objectClass", "inetOrgPerson", "posixAccount", "top");
        entry.addAttribute("uidNumber", "500");
        entry.addAttribute("gidNumber", "113");

        return entry;
    }
}
