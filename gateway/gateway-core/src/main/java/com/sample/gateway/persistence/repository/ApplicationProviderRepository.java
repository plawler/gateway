package com.sample.gateway.persistence.repository;

import com.sample.gateway.persistence.domain.ApplicationProviderEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lloydengebretsen on 2/17/14.
 */
public interface ApplicationProviderRepository extends CrudRepository<ApplicationProviderEntity, Long> {
    ApplicationProviderEntity findByApplicationProviderName(String applicationProviderName);
}
