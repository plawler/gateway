package org.inbloom.gateway.credentials.ldap;

import com.unboundid.ldap.sdk.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created By: paullawler
 */
@Component
public class LdapService {

    @Autowired
    LDAPConnection connection;

    public void addEntry(Entry entry) throws LDAPException {
        LDAPResult added = connection.add(new AddRequest(entry));
    }

    public Entry findEntry() {
        return null;
    }

    public void modifyEntry(Entry entry) {

    }
}
