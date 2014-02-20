package com.sample.gateway.util.security;

import com.sample.gateway.persistence.repository.ApplicationRepository;
import com.sample.gateway.util.security.ApplicationKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Override
    public String generateKey(Integer length) {
        return "somerandomkeynot";
    }

}
