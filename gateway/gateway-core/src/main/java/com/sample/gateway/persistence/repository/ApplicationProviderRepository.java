package com.sample.gateway.persistence.repository;

import com.sample.gateway.persistence.domain.ApplicationProvider;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lloydengebretsen on 2/17/14.
 */
public interface ApplicationProviderRepository extends CrudRepository<ApplicationProvider, Long> {
    ApplicationProvider findByApplicationProviderName(String applicationProviderName);
}
