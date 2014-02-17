package com.sample.gateway.persistence.integration;

import com.sample.gateway.configuration.JPAConfiguration;
import com.sample.gateway.persistence.domain.Application;
import com.sample.gateway.persistence.domain.ApplicationProvider;
import com.sample.gateway.persistence.repository.ApplicationProviderRepository;
import com.sample.gateway.persistence.repository.ApplicationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static junit.framework.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/14/14
 * Time: 4:51 PM
 * To change this template use File | Settings | File Templates.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ApplicationRepositoryTest {

    @Autowired
    private ApplicationRepository repository;
    @Autowired
    private ApplicationProviderRepository applicationProviderRepository;

    @Test
    public void shouldInsertApplicationIntoRepo() {
        ApplicationProvider applicationProvider = new ApplicationProvider("Some Guy");
        applicationProvider.setAccountConfirmed(true);
        applicationProvider.setApplicationProviderName("Blue Sun Apps Inc.");
        applicationProvider.setOrganizationName("Blue Sun");
        applicationProvider.setTermsAccepted(true);
        applicationProvider.setUsername("blusun");
        applicationProvider = applicationProviderRepository.save(applicationProvider);



        Application application = new Application();

//        application.setApplicationId(1);
        application.setApplicationName("Lloyd and Paul's Crazy Edu App");
        application.setClientId("123456");
        application.setSharedSecret("asdjhgsdfjgsadjfgkjhgasdjfgjksadghfkjhg");
        application.setCreatedAt(new Date());
        application.setCreatedBy("plawler");
        application.setApplicationProvider(applicationProvider);

        repository.save(application);

        Application retrieved = repository.findByApplicationName("Lloyd and Paul's Crazy Edu App");
        assertNotNull(retrieved);
        assertEquals(applicationProvider.getApplicationProviderId(), application.getApplicationProvider().getApplicationProviderId());
        assertEquals(applicationProvider.getUsername(), application.getApplicationProvider().getUsername());
    }
}
