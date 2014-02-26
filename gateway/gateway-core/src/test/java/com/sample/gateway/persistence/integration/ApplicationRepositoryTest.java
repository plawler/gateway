package com.sample.gateway.persistence.integration;

import com.sample.gateway.configuration.JPAConfiguration;
import com.sample.gateway.persistence.domain.ApplicationEntity;
import com.sample.gateway.persistence.domain.ApplicationProviderEntity;
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
        ApplicationProviderEntity applicationProviderEntity = new ApplicationProviderEntity("Some Guy");
        applicationProviderEntity.setAccountConfirmed(true);
        applicationProviderEntity.setApplicationProviderName("Blue Sun Apps Inc.");
        applicationProviderEntity.setOrganizationName("Blue Sun");
        applicationProviderEntity.setTermsAccepted(true);
        applicationProviderEntity.setUsername("blusun");
        applicationProviderEntity = applicationProviderRepository.save(applicationProviderEntity);

        ApplicationEntity applicationEntity = new ApplicationEntity();

        applicationEntity.setApplicationName("Lloyd and Paul's Crazy Edu App");
        applicationEntity.setClientId("123456");
        applicationEntity.setSharedSecret("asdjhgsdfjgsadjfgkjhgasdjfgjksadghfkjhg");
        applicationEntity.setCreatedAt(new Date());
        applicationEntity.setCreatedBy("plawler");
        applicationEntity.setApplicationProviderEntity(applicationProviderEntity);

        repository.save(applicationEntity);

        ApplicationEntity retrieved = repository.findByApplicationName("Lloyd and Paul's Crazy Edu App");

        assertNotNull(retrieved);
        assertEquals(applicationProviderEntity.getApplicationProviderId(), applicationEntity.getApplicationProviderEntity().getApplicationProviderId());
        assertEquals(applicationProviderEntity.getUsername(), applicationEntity.getApplicationProviderEntity().getUsername());
    }

    @Test
    public void shouldUpdateAnApplicationAndSaveInRepo() {
        ApplicationEntity applicationEntity = new ApplicationEntity();

        applicationEntity.setApplicationName("My App");
        applicationEntity.setClientId("123456");
        applicationEntity.setSharedSecret("asdjhgsdfjgsadjfgkjhgasdjfgjksadghfkjhg");
        applicationEntity.setCreatedAt(new Date());
        applicationEntity.setCreatedBy("plawler");

        repository.save(applicationEntity);

        ApplicationEntity retrieved = repository.findByApplicationName("My App");
        retrieved.setApplicationName("My Modified App");

        repository.save(retrieved);

        ApplicationEntity retrievedAgain = repository.findByApplicationName("My Modified App");

        assertEquals("My Modified App", retrievedAgain.getApplicationName());
    }
}
