package com.sample.gateway.util.security;

import com.sample.gateway.util.security.ApplicationKeyGenerator;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/20/14
 * Time: 8:52 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationKeyGeneratorFactory {

    ApplicationKeyGenerator getKeyGenerator(String generatorName);

}
