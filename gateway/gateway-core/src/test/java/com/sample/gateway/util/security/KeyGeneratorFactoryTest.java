package com.sample.gateway.util.security;

import com.sample.gateway.persistence.repository.ApplicationRepository;
import com.sample.gateway.util.security.ApplicationKeyGenerator;
import com.sample.gateway.util.security.ApplicationKeyGeneratorFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.*;


/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/20/14
 * Time: 9:02 AM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:gateway-core/src/main/webapp/WEB-INF/applicationContext.xml"})
public class KeyGeneratorFactoryTest {

    @Autowired
    private ApplicationKeyGeneratorFactory keyGeneratorFactory;

    @Autowired
    private ApplicationRepository repository;

    @Test
    public void shouldReturnANewKeyGenerator() {
        ApplicationKeyGenerator generator = keyGeneratorFactory.getKeyGenerator("secureRandomKeyGenerator");
        assertNotNull(generator.generateClientId());
        assertNotNull(generator.generateSharedSecret());

    }

}