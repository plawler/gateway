package com.sample.gateway.persistence.repository;

import com.sample.gateway.persistence.domain.ApplicationEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/14/14
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationRepository extends CrudRepository<ApplicationEntity, Long> {

    ApplicationEntity findByApplicationName(String applicationName);

    ApplicationEntity findByClientId(String clientId);

}
