package com.sample.gateway.persistence.integration;

import com.sample.gateway.configuration.JPAConfiguration;
import com.sample.gateway.persistence.domain.fixture.JPAAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Created by lloydengebretsen on 2/15/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OperatorTableMappingTest {

    @Autowired
    EntityManager entityManager;

    @Test
    public void shouldSupportCustomMapping() {

        JPAAssertions.assertTableExists(entityManager, "operators");
        JPAAssertions.assertTableHasColumn(entityManager, "operators", "operator_id");
        JPAAssertions.assertTableHasColumn(entityManager, "operators", "operator_name");
        JPAAssertions.assertTableHasColumn(entityManager, "operators", "api_uri");
        JPAAssertions.assertTableHasColumn(entityManager, "operators", "operator_id");
        JPAAssertions.assertTableHasColumn(entityManager, "operators", "is_enabled");
        JPAAssertions.assertTableHasColumn(entityManager, "operators", "connector_uri");
        JPAAssertions.assertTableHasColumn(entityManager, "operators", "contract_start_on");
        JPAAssertions.assertTableHasColumn(entityManager, "operators", "contract_end_on");

    }
}
