package com.sample.gateway.persistence.integration;

import com.sample.gateway.configuration.JPAConfiguration;
import com.sample.gateway.persistence.domain.Application;
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

    @Test
    public void shouldInsertApplicationIntoRepo() {
        Application application = new Application();

//        application.setApplicationId(1);
        application.setApplicationName("Lloyd and Paul's Crazy Edu App");
        application.setClientId("123456");
        application.setSharedSecret("asdjhgsdfjgsadjfgkjhgasdjfgjksadghfkjhg");
        application.setCreatedAt(new Date());
        application.setCreatedBy("plawler");

        repository.save(application);

        Application retrieved = repository.findByApplicationName("Lloyd and Paul's Crazy Edu App");
        assertNotNull(retrieved);
    }
}
