package com.sample.gateway.util.security;

import com.sample.gateway.persistence.repository.ApplicationRepository;
import com.sample.gateway.util.security.ApplicationKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/20/14
 * Time: 8:36 AM
 * To change this template use File | Settings | File Templates.
 */
@Component("SecureRandomKeyGenerator")
public class SecureRandomKeyGenerator implements ApplicationKeyGenerator {

    @Autowired
    private ApplicationRepository applicationRepository; // todo: please remove this dependency


    private static final int CLIENT_ID_LENGTH = 10;
    private static final int CLIENT_SECRET_LENGTH = 48;
    private static final char[] DEFAULT_CODEC = "1234567890abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static Random random = new SecureRandom();


    public String generateSharedSecret() {
        return generateToken(CLIENT_SECRET_LENGTH);
    }

    public String generateClientId() {
        String clientId = generateToken(CLIENT_ID_LENGTH);
        while(applicationRepository.findByClientId(clientId)!=null) // TODO: revisit
            clientId = generateToken(CLIENT_ID_LENGTH);
        return clientId;
    }

    /**straight up copy-and-pasted from TokenGrabber.java in SDS project**/

    /**
     * Generates a random string containing characters a-z, A-Z, 0-9
     *
     * @param length length of string
     * @return a securely random string
     */
    public static String generateToken(int length) {
        byte[] verifierBytes = new byte[length];
        random.nextBytes(verifierBytes);
        return getAuthorizationCodeString(verifierBytes);
    }

    /**
     * Grabbed this out of org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices
     *
     * @param verifierBytes The bytes.
     * @return The string.
     */
    private static String getAuthorizationCodeString(byte[] verifierBytes) {
        char[] chars = new char[verifierBytes.length];
        for (int i = 0; i < verifierBytes.length; i++) {
            chars[i] = DEFAULT_CODEC[((verifierBytes[i] & 0xFF) % DEFAULT_CODEC.length)];
        }
        return new String(chars);
    }

}
