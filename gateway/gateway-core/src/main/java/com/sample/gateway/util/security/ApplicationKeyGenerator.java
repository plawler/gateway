package com.sample.gateway.util.security;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/20/14
 * Time: 8:34 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationKeyGenerator {

    String generateClientId();
    String generateSharedSecret();

}
