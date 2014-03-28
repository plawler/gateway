package org.inbloom.gateway.credentials.ldap;

import com.unboundid.ldap.sdk.*;
import com.unboundid.ldif.LDIFException;
import org.inbloom.gateway.core.event.CreateCredentialsEvent;

import java.util.Map;

/**
 * Created By: paullawler
 */
public class LdapRequestFactory {

    private static final String PEOPLE_BASE_DN = "ou=people,ou=LocalNew,ou=DevTest,dc=slidev,dc=org";
    private static final String GROUP_BASE_DN = "ou=groups,ou=LocalNew,ou=DevTest,dc=slidev,dc=org";

    public static AddRequest newPersonRequest(String firstName, String lastName, String email, String password)
            throws LDAPException {
        DN dn = new DN("cn=" + email + "," + PEOPLE_BASE_DN);
        Entry entry = new Entry(dn);

        entry.addAttribute("givenName", firstName);
        entry.addAttribute("sn", lastName);
        entry.addAttribute("mail", email);
        entry.addAttribute("userPassword", password);

        // standard stuff
        entry.addAttribute("objectClass", "inetOrgPerson", "posixAccount", "top");
        entry.addAttribute("uid", email);
        entry.addAttribute("uidNumber", "500");
        entry.addAttribute("gidNumber", "113");
        entry.addAttribute("loginShell", "/sbin/nologin");
        entry.addAttribute("homeDirectory", "/dev/null");

        return new AddRequest(entry);
    }

    public static ModifyRequest newAddToAppDeveloperGroupRequest(String email) throws LDAPException {
        DN dn = new DN("cn=application_developer," + GROUP_BASE_DN);
        Modification mod = new Modification(ModificationType.ADD, "memberUid", email);
        return new ModifyRequest(dn, mod);
    }

    public static ModifyRequest newAddToSanboxAdminRequest(String email) throws LDAPException {
        DN dn = new DN("cn=Sandbox Administrator," + GROUP_BASE_DN);
        Modification mod = new Modification(ModificationType.ADD, "memberUid", email);
        return new ModifyRequest(dn, mod);
    }

}
