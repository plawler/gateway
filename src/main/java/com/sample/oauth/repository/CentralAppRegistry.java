package com.sample.oauth.repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sample.oauth.model.APIService;
import com.sample.oauth.model.IdPService;
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
        realms.add(new Realm(UUID.randomUUID(), "inBloom"));
//        realms.add(new Realm(UUID.randomUUID(), "Daybreak School District 4529"));

        for (Realm realm : realms) {
//            services.put(realm, new IdPService(UUID.randomUUID(), "http://localhost:9000"));
            services.put(realm, new APIService(UUID.randomUUID(), "https://api.sandbox.inbloom.org/"));
        }
    }

    public List<Realm> getRealms() {
        return realms;
    }

    public Service findIdPByRealm(String realmId) {
        return null;
    }

    public Service findDataServiceByRealm(String realmId) {
        Realm realm = findRealmById(realmId);
        return services.get(realm);
    }

    private Realm findRealmById(String realmId) {
        for (Realm realm : realms) {
            if (realm.getIdentifier().toString().equals(realmId)) {
                return realm;
            }
        }
        return null;
    }

    public Service findDataServiceByRealmAndApplication() {
        return null;
    }
}
