package com.sample.gateway.persistence.repository;

import com.sample.gateway.persistence.domain.Application;
import org.springframework.data.repository.CrudRepository;

/**
 * Created with IntelliJ IDEA.
 * User: paullawler
 * Date: 2/14/14
 * Time: 4:22 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ApplicationRepository extends CrudRepository<Application, Long> {

    Application findByApplicationName(String applicationName);

}
