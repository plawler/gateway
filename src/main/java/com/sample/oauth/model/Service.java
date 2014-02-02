package com.sample.oauth.model;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 1/28/14
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class Service {

    private final UUID identifier;
    private final String url;
    private final String uniqueRealmIdentifier;

    public Service(UUID identifier, String url, String uniqueRealmIdentifier) {
        this.identifier = identifier;
        this.url = url;
        this.uniqueRealmIdentifier = uniqueRealmIdentifier;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public String getUrl() {
        return url;
    }

    public String getUniqueRealmIdentifier() {
        return uniqueRealmIdentifier;
    }

}
