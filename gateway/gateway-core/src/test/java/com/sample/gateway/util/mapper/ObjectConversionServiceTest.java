package com.sample.gateway.util.mapper;

import com.sample.gateway.core.domain.Application;
import com.sample.gateway.persistence.domain.ApplicationEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.sample.gateway.rest.fixture.ApplicationEventFixtures.registerApplication;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/23/14
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
public class ObjectConversionServiceTest {

    @Autowired
    ConversionService conversionService;

    @Test
    public void shouldMapADomainToPersistenceModel() {
        assertTrue(true);
        Application domain = registerApplication();
        ApplicationEntity persistent = conversionService.convert(domain, ApplicationEntity.class);
        assertNotNull(persistent);
        assertEquals(persistent.getApplicationName(), domain.getApplicationName());
    }

}
