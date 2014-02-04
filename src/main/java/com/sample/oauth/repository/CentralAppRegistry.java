package com.sample.oauth.repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sample.oauth.model.Realm;
import com.sample.oauth.model.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 1/30/14
 * Time: 11:11 AM
 * To change this template use File | Settings | File Templates.
 */
@Component
public class CentralAppRegistry {

    private static final Logger log = LoggerFactory.getLogger(CentralAppRegistry.class);

    private List<Realm> realms = Lists.newArrayList();
    private Map<Realm, Service> services = Maps.newHashMap();

    @PostConstruct
    public void initializeData() {
        log.info("initializing data");

        Realm realm1 = new Realm(UUID.randomUUID(), "Illinois Daybreak School District 4529");
        realms.add(realm1);
        services.put(realm1, new Service(UUID.randomUUID(), "http://paul.slidev.org:8080/", "IL-Daybreak"));

        Realm realm2 = new Realm(UUID.randomUUID(), "Illinois Sunset School District 4526");
        realms.add(realm2);
        services.put(realm2, new Service(UUID.randomUUID(), "http://lloyd.slidev.org:8080/", "IL-Sunset"));
    }

    public List<Realm> getRealms() {
        return realms;
    }

    public Service findDataServiceByRealm(String realmId) {
        Realm realm = findRealmById(realmId);
        return services.get(realm);
    }

    private Realm findRealmById(String realmId) {
        for (Realm realm : realms) {
            if (realm.getIdentifier().equals(UUID.fromString(realmId))) {
                return realm;
            }
        }
        return null;
    }

}
