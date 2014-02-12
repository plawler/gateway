package com.sample.gateway.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 1/28/14
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class Realm implements Serializable {

    private final UUID identifier;
    private final String name;

    public Realm(UUID identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

}
