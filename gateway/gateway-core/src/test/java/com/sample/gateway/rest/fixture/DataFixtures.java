package com.sample.gateway.rest.fixture;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/17/14
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataFixtures {

    public static String applicationJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(ApplicationEventFixtures.registerApplication());
    }

}
