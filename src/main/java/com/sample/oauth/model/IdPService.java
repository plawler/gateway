package com.sample.oauth.model;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 1/30/14
 * Time: 9:52 AM
 * To change this template use File | Settings | File Templates.
 */
public class IdPService extends Service {

    public IdPService(UUID identifier, String url) {
        super(identifier, url);
    }

}
