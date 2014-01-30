package com.sample.oauth.model;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 1/28/14
 * Time: 2:05 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Service {

    private final UUID identifier;
    private final String url;

    public Service(UUID identifier, String url) {
        this.identifier = identifier;
        this.url = url;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public String getUrl() {
        return url;
    }

}
