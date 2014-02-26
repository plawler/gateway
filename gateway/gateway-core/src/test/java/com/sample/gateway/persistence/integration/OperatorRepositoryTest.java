package com.sample.gateway.persistence.integration;

import com.sample.gateway.configuration.JPAConfiguration;
import com.sample.gateway.persistence.domain.OperatorEntity;
import com.sample.gateway.persistence.repository.OperatorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.*;

/**
 * Created by lloydengebretsen on 2/15/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JPAConfiguration.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OperatorRepositoryTest {

    @Autowired
    private OperatorRepository repository;

    @Test
    public void shouldInsertOperatorIntoRepo() {
        String createdBy = "Some Guy";
        OperatorEntity operatorEntity = new OperatorEntity(createdBy);
        operatorEntity.setOperatorName("A Number One Best OperatorEntity");
        operatorEntity.setEnabled(true);

        repository.save(operatorEntity);

        OperatorEntity retrieved = repository.findByOperatorName("A Number One Best OperatorEntity");
        assertNotNull(retrieved);
        assertNotNull(retrieved.getCreatedAt());
        assertEquals("", createdBy, retrieved.getCreatedBy());
        assertTrue(retrieved.isEnabled());
    }

    @Test
    public void shouldDeleteOperatorFromRepo() {
        String createdBy = "Some Other Guy";
        String operatorName = "OperatorEntity the Second";
        OperatorEntity operatorEntity = new OperatorEntity(createdBy);
        operatorEntity.setOperatorName(operatorName);

        repository.save(operatorEntity);

        OperatorEntity retrieved = repository.findByOperatorName(operatorName);

        repository.delete(retrieved);

        assertNull(repository.findByOperatorName(operatorName));
    }

}
