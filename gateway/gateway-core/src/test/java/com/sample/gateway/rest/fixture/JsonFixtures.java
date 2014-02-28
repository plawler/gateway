package com.sample.gateway.rest.fixture;

import com.sample.gateway.core.domain.Operator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/17/14
 * Time: 1:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class JsonFixtures {

    public static String applicationJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(ApplicationEventFixtures.registerApplication());
    }

    public static String operatorJson() throws IOException {
        return stringify(OperatorEventFixtures.registerOperator());
    }

    public static String invalidOperatorJson() throws IOException {
        return stringify(OperatorEventFixtures.registerInvalidOperator());
    }

    private static String stringify(Operator operator) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(operator);
    }

}
